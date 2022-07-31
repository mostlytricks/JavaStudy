package com.mostlytricks.study.web.dto;

import com.mostlytricks.study.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        // Entity를 받아서 특정 요소만 응답함. 모든 필드를 전달할 필요는 없다.
        // repository -> view로 가기에 entity를 추가로 조작할 필요가 없다.
        this.id = entity.getId();
        this.title= entity.getTitle();
        this.content = entity.getContent();
        this.author= entity.getAuthor();
    }

}
