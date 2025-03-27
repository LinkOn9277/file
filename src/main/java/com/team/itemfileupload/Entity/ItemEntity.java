/*********************************************************
 * 상품정보를 관리하는 테이블
 *
 * Entity 설계기본 : @Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor @Entity
 *
 * 테이블 정보
 *
 * @SequenceGenerator : 자동증가 설정
 *********************************************************/
package com.team.itemfileupload.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "item")
@SequenceGenerator(

        name = "item_seq" ,
        sequenceName = "item_seq" ,
        initialValue = 1 ,
        allocationSize = 4

)
public class ItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "item_seq")
    private Integer ino;        // 번호

    @Column(name = "item" , length = 100)
    private String item;        // 상품명

    @Column(name = "detail" , length = 200)
    private String detail;      // 상품정보

    @Column(name = "quantity")
    private Integer quantity=0;   // 수량

    @Column(name = "price")
    private Integer price=0;      // 가격

    @Column(name = "sale")
    private Integer sale;       // 세일상태(1- 정상 , 2-세일)

    @Column(name = "state")
    private Integer state;      // 상품(1- 신상품, 2-판매상품, 3-이월상품)

    @Column(name = "img")
    private String img;         // 이미지파일명


}
