/***********************************************
 * 파일 업로드 관련 클래스
 ************************************************/
package com.team.itemfileupload.Util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Component
public class FileUpload {
    /*********************************************************
     * 메소드명 : FileUpload()
     * 인수값 : 저장위치 , 저장할 파일명
     * 결과값 : 새로 생성된 파일명
     * 기 능 : 지정된 파일명을 새로운 이름을 생성해서 지정한 위치에 저장
     *********************************************************/

    public String FileUpload(String imgLocation, MultipartFile imageFile) {
        // imageFile → 파일명 분리(sample.jpg)
        String originalFilename = imageFile.getOriginalFilename();
        // 확장자 분리(sample.jpg →→→ .jpg)
        // lastIndexOf : 지정문자가 위치하는 마지막위치
        // substring(시작 , 끝) : 지정한 위치부터 지정한 갯수만큼 문자열을 추출
        // sasm.sfte.sample'.(lastIndexOf)jpg'substring
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 난수로 문자를 조합해서 생성 adfe232rrf-dfadfe323-afew343(중복 파일명을 방지하기 위해서 사용)
        UUID uuid = UUID.randomUUID();
        // 새로운 파일명을 만듦(adfe232rrf-dfadfe323-afew343.jpg)→→→→→다른 이름 저장
        String saveFileName = uuid.toString()+extension;
        // 최종 저장되는 위치
        // item/image/adfe232rrf-dfadfe323-afew343.jpg
        String uploadUrl = imgLocation + "/" + saveFileName;
        //====================기본 변수 셋팅 끝=======================================================

        // 외부작업은 반드시 try 적용
        try {
            File folder = new File(imgLocation);
            if (!folder.exists()) { // 해당 폴더가 존재하지 않으면
                boolean result = folder.mkdir(); // 폴더를 생성한다.
            }
            byte[] filedata = imageFile.getBytes(); // 이미지를 바이트 단위로 읽어온다.
            // 해당 위치에 파일을 연속적으로 출력
            FileOutputStream fos = new FileOutputStream(uploadUrl);
            fos.write(filedata); // 파일쓰기
            fos.close();         // 쓰기가 완료되면 파일을 닫는다.
        }catch (Exception e){
            return null; // 저장실패시 파일명 없이 돌아간다.
        }
        return saveFileName; // 저장 성공시 새로운 파일명을 가지고 돌아간다.
    }

    /*********************************************************
     * 메소드명 : FileDelete()
     * 인수값 : 저장된 위치 , 저장된 파일명
     * 결과값 :
     * 기 능 : 지정된 위치에서 파일을 삭제
     *********************************************************/

    public void FileDelete(String imgLocation , String imgFileName) {
        String deleteFileName = imgLocation + imgFileName;

        try {
            File deleteFile = new File(deleteFileName);
            if (deleteFile.exists()) { // 해당파일이 존재하면
                deleteFile.delete();   // 파일을 삭제
            }
        }catch (Exception e){

        }
    }


}
