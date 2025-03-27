/*********************************************************
 * 상품처리를 위한 클래스
 *
 * @Value("${imgUploadLocation}") : properties → 필요한 값을 읽어온다.(작업정보)
 * orElseThrow() : try 대신해서 사용가능
 *********************************************************/
package com.team.itemfileupload.Service;

import com.team.itemfileupload.DTO.ItemDTO;
import com.team.itemfileupload.Entity.ItemEntity;
import com.team.itemfileupload.Repository.ItemRepository;
import com.team.itemfileupload.Util.FileUpload;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    @Value("${imgUploadLocation}")
    String imgUploadLocation;

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final FileUpload fileUpload;

    /*********************************************************
     * 메소드명 : itemListView()
     * 인수값 :
     * 결과값 : List<DTO>
     * 기 능 : 모든 데이터를 조회해서 전달
     *********************************************************/
    public List<ItemDTO> allDate(){
        // 전체조회
        List<ItemEntity> itemEntities = itemRepository.findAll();

        List<ItemDTO> itemDTOS = Arrays.asList(modelMapper.map(itemEntities , ItemDTO[].class));

        return itemDTOS;
    }

    /*********************************************************
     * 메소드명 : itemDelete()
     * 인수값 : id
     * 결과값 :
     * 기 능 : 해당 id의 데이터를 삭제
     *********************************************************/
    public void itemDelete(Integer ino){
        itemRepository.deleteById(ino);
    }

    /*********************************************************
     * 메소드명 : itemRead()
     * 인수값 : id
     * 결과값 : DTO
     * 기 능 : 해당 id의 데이터를 조회해서 전달
     *********************************************************/
    public ItemDTO itemRead(Integer ino){
        // 해당 id를 조회해서 없으면 오류발생
        ItemEntity itemEntity = itemRepository.findById(ino).orElseThrow();
        ItemDTO itemDTO = modelMapper.map(itemEntity, ItemDTO.class);
        return itemDTO;
    }

    /*********************************************************
     * 메소드명 : itemSave()
     * 인수값 : DTO , MultiPart
     * 결과값 :
     * 기 능 : 해당 상품정보와 관련이미지를 저장
     *********************************************************/
    public void itemSave(ItemDTO itemDTO , MultipartFile imgFile){
        // 파일을 저장
        String newFileName = fileUpload.FileUpload(imgUploadLocation , imgFile);
        if (newFileName != null || newFileName.length() > 0){ // 이미지 파일을 저장 성공했으면
            // 저장된 파일의 새로운 이름을 다시 변수에 저장
            itemDTO.setImg(newFileName);
        }

        // 변환
        ItemEntity itemEntity = modelMapper.map(itemDTO, ItemEntity.class);
        itemRepository.save(itemEntity);
    }

    /*********************************************************
     * 메소드명 : itemModify()
     * 인수값 : DTO , MultiPart
     * 결과값 :
     * 기 능 : 해당 상품정보와 관련이미지로 수정
     *********************************************************/
    public void itemModify(ItemDTO itemDTO , MultipartFile imgFile){
        // 새로운 이미지가 있으면 기존파일 삭제
        if (!imgFile.isEmpty() || imgFile != null){ // 새로운 이미지 파일이 존재하면
            // 해당 데이터를 조회
            ItemEntity itemEntity = itemRepository.findById(itemDTO.getIno()).orElseThrow();
            // 기존 이미지파일명을 읽어온다.
            String deleteFile = itemEntity.getImg();
            // 기존 이미지파일을 삭제
            fileUpload.FileDelete(imgUploadLocation , deleteFile);

            // 새로운 이미지를 저장
            String newImgFile = fileUpload.FileUpload(imgUploadLocation , imgFile);
            // 새로운 이미지파일명을 저장
            itemDTO.setImg(newImgFile);
        }
        // 변환작업 후
        ItemEntity itemEntity = modelMapper.map(itemDTO, ItemEntity.class);
        // 데이터베이스에 저장
        itemRepository.save(itemEntity);
    }



}
