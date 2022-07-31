package com.mostlytricks.study.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();

    }
    @Test // test실패시 setting > build... > tools > run test using : intellij...
    public void testLoad(){
         String title = "test posts";
         String content = "test body text";

         Posts tempPost = Posts.builder()
                 .title(title)
                 .content(content)
                 .author("mostlytricks")
                 .build();
         postsRepository.save(
                 tempPost
         ) ;

         List<Posts> postsList = postsRepository.findAll();

         Posts posts = postsList.get(0);
         assertThat(posts.getTitle()).isEqualTo(title);
         assertThat(posts.getContent()).isEqualTo(content);
        System.out.println(posts.getTitle());
    }

}
