package com.mostlytricks.study.domain.posts;


import com.mostlytricks.study.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 추가
@Entity // JPA, className to TABLE_NAME
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 500, nullable= false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author; // @Column 선언치 않아도 자동 Column 지정

    @Builder(toBuilder = true) // 이 경우 생성자 포함된 필드만 빌더
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update( String title, String content){
        this.content = content;
        this.title = title;
    }
    /*
    * No Setter.
    * Entity class내 미사용 권장.
    * setStatus(false) 보단 cancelOrder() 만들어서 객체 관점.
    * */
}
