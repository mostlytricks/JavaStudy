package com.mostlytricks.study.web;

import com.mostlytricks.study.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @Autowired // controller 상단의 required..와 동격, 생성자를 통한 의존성 주입
    public IndexController(PostsService postsService){
        this.postsService = postsService;
    }

    @GetMapping("/")
    public String index(){
        return "index.html";
    }
}
