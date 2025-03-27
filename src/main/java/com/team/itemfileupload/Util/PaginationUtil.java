/*********************************************************
 * 페이지 정보 관련 클래스
 *********************************************************/
package com.team.itemfileupload.Util;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaginationUtil {
    /*********************************************************
     * 메소드명 : Pagination()
     * 인수값 : 페이지 정보
     * 결과값 : Map(페이지 정보)
     * 기 능 : 페이지 정보를 가지고 세부 페이지 정보를 구한다.
     *********************************************************/
    public static Map<String , Integer> Pagination(Page<?> page){
        // 전체 레코드 수
        long totalRecordsLong = page.getTotalElements();
        // 총 레코드의 수가 정수형 범위를 벗어나면 최대 정수값을 적용
        int totalRecords = (totalRecordsLong > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int)totalRecordsLong;

        // 현재페이지 번호
        int currentPage = page.getNumber()+1;
        // 전체 페이지 수
        int totalPages = page.getTotalPages();
        // 페이지번호 블럭수
        int blockLimit = 10;

        Map<String , Integer> pageInfo = new HashMap<>();

        // 오류 방지를 위해서 최초 초기값 설정
        if (totalPages == 0){                               // 전체 페이지가 1페이지이면
            pageInfo.put("startPage",1);                    // 첫 페이지
            pageInfo.put("prevPage",1);                     // 이전 페이지
            pageInfo.put("currentPage",1);                  // 현재 페이지
            pageInfo.put("endPage",1);                      // 페이지블럭의 마지막
            pageInfo.put("nextPage",1);                     // 다음 페이지
            pageInfo.put("lastPage",1);                     // 마지막 페이지
            pageInfo.put("totalRecords" , totalRecords);    // 전체 레코드 수
            return pageInfo;
        }
        // 페이지수가 1페이지 이상이면
        int startPage = Math.max(1, currentPage - blockLimit/2);
        int endPage = Math.min(startPage + blockLimit-1 , totalPages);
        startPage = Math.max(1, endPage - blockLimit+1);
        int prevPage = Math.min(totalPages , currentPage-1);
        int nextPage = Math.min(totalPages , currentPage+1);
        int lastPage = totalPages;

        pageInfo.put("startPage", startPage);                    // 첫 페이지
        pageInfo.put("prevPage", prevPage);                      // 이전 페이지
        pageInfo.put("currentPage", currentPage);                // 현재 페이지
        pageInfo.put("endPage", endPage);                        // 페이지블럭의 마지막
        pageInfo.put("nextPage", nextPage);                      // 다음 페이지
        pageInfo.put("lastPage", lastPage);                      // 마지막 페이지
        pageInfo.put("totalRecords" , totalRecords);             // 전체 레코드 수

        return pageInfo;
    }


}
