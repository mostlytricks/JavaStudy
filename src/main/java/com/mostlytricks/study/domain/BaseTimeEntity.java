package com.mostlytricks.study.domain;


/*
* JPA Auditing을 이용한 생성시간/수정시간 자동화하기
* - 본 Entity상속하여 엔터티 구현시 자동 시간기록 가능
* - hibernate 5.1 이후에 LocalDateTime이 DB에 잘 맵핑됨 유의할 것
*
*
* */
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity가 본 클래스 상속시, 하위 필드도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate // Entity생성시각 기록
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
