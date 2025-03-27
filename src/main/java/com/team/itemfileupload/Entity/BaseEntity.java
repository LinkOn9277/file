/*********************************************************
 * 여러테이블에서 사용하는 공통필드
 *
 * @MappedSuperclass : 제공용 클래스
 * @EntityListeners(AuditingEntityListener.class) : 날짜 자동생성
 * 변수 → 변수역할 지정
 * 공용필드가 완성이 되면 작업 테이블로 이동
 *********************************************************/
package com.team.itemfileupload.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(name = "regDate" , nullable = false , updatable = false)
    @CreatedDate
    private LocalDateTime regDate;

    @Column(name = "modDate")
    @LastModifiedDate
    private LocalDateTime modDate;

}
