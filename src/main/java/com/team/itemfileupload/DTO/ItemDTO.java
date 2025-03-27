package com.team.itemfileupload.DTO;

import lombok.*;

import java.time.LocalDateTime;
@Setter @Getter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class ItemDTO {

    private Integer ino;        // 번호

    private String item;        // 상품명

    private String detail;      // 상품정보

    private Integer quantity;   // 수량

    private Integer price;      // 가격

    private Integer sale;       // 세일상태(1- 정상 , 2-세일)

    private Integer state;      // 상품(1- 신상품, 2-판매상품, 3-이월상품)

    private String img;         // 이미지파일명

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}
