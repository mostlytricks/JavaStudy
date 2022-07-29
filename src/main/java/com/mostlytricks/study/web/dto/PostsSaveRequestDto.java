package com.mostlytricks.study.web.dto;

import com.mostlytricks.study.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author= author;
    }

    public Posts toEntity(){
        /* Entity와 유사한 Dto임에도 분리함 유의할 것. Setter안쓴부분도 기억 해두자.*/
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
