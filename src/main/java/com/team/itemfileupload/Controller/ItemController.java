/*********************************************************
 * 상품 제어를 위한 클래스
 *********************************************************/
package com.team.itemfileupload.Controller;

import com.team.itemfileupload.DTO.ItemDTO;
import com.team.itemfileupload.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /*********************************************************
     * 메소드명 : itemListView()
     * 인수값 :
     * 결과값 : List<DTO>
     * 기 능 : 조회한 모든 데이터를 list.html 전달
     *********************************************************/
    @GetMapping(value = {"/", "/item/list"})
    public String itemListView(Model model) {
        // Service 처리
        List<ItemDTO> itemDTOS = itemService.allDate();
        itemDTOS.sort((o1, o2) -> o2.getIno().compareTo(o1.getIno()));
        // 값 전달
        model.addAttribute("list", itemDTOS);
        return "/item/list"; // 이동페이지
    }

    /*********************************************************
     * 메소드명 : itemInsertView()
     * 인수값 :
     * 결과값 :
     * 기 능 : 등록페이지(insert.html)로 이동
     *********************************************************/
    @GetMapping("/item/insert")
    public String itemInsertView() {

        return "/item/insert"; // 이동페이지
    }

    /*********************************************************
     * 메소드명 : itemInsertProc()
     * 인수값 : DTO , MultiPart
     * 결과값 :
     * 기 능 : 받아온 DTO와 파일을 저장하고, 목록페이지(list.html)로 이동
     *********************************************************/
    @PostMapping("/item/insert")
    public String itemInsertProc(ItemDTO itemDTO , MultipartFile imgFile) {
        itemService.itemSave(itemDTO, imgFile);
        return "redirect:/item/list"; // 이동페이지
    }

    /*********************************************************
     * 메소드명 : itemDeleteProc()
     * 인수값 : id
     * 결과값 :
     * 기 능 : 해당 Id에 데이터를 삭제하고, 목록페이지(list.html)로 이동
     *********************************************************/
    @GetMapping("/item/delete")
    public String itemDeleteProc(Integer ino) {
        itemService.itemDelete(ino);
        return "redirect:/item/list"; // 이동페이지
    }

    /*********************************************************
     * 메소드명 : itemDetailProc()
     * 인수값 : id
     * 결과값 :
     * 기 능 : 해당 Id에 데이터를 조회해서 상세페이지(detail.html)에 전달
     *********************************************************/
    @GetMapping("/item/detail")
    public String itemDetailProc(Integer ino , Model model) {
        ItemDTO itemDTO = itemService.itemRead(ino);
        model.addAttribute("data", itemDTO);
        return "/item/detail"; // 이동페이지
    }

    /*********************************************************
     * 메소드명 : itemModifyView()
     * 인수값 : id
     * 결과값 :
     * 기 능 : 해당 Id에 데이터를 조회해서 수정페이지(modify.html)에 전달
     *********************************************************/
    @GetMapping("/item/modify")
    public String itemModifyView(Integer ino , Model model) {
        ItemDTO itemDTO = itemService.itemRead(ino);
        model.addAttribute("data", itemDTO);
        return "/item/modify"; // 이동페이지
    }

    /*********************************************************
     * 메소드명 : itemModifyProc()
     * 인수값 : DTO , MultiPart
     * 결과값 :
     * 기 능 : 수정된 정보와 이미지를 저장하고 목록페이지(list.html)로 이동
     *********************************************************/
    @PostMapping("/item/modify")
    public String itemModifyProc(ItemDTO itemDTO , MultipartFile imgFile) {
        itemService.itemModify(itemDTO, imgFile);
        return "redirect:/item/list"; // 이동페이지
    }
}
// 틀이 바뀔 부분이 없다.
// REST_Full 방식을 사용할 때는 삽입 , 수정 부분에서 for , if문을 사용
//  REST 적용하는 메소드에 try 사용

// Controller
// 요청이 들어오면 응답(데이터값, 이동페이지)
// 요청 페이지와 응답 페이지가 다르게 작업이 가능

// RestController
// 요청이 들어오면 응답(데이터 값 , 성공여부)
// 요청 페이지와 응답 페이지가 동일
