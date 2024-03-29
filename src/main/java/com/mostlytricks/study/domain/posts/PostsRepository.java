package com.mostlytricks.study.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    // Querydsl을 이용한 조회구문
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findALLDesc();

}
