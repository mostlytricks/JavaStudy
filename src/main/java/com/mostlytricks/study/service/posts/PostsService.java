package com.mostlytricks.study.service.posts;

import com.mostlytricks.study.domain.posts.Posts;
import com.mostlytricks.study.domain.posts.PostsRepository;
import com.mostlytricks.study.web.dto.PostsListResponseDto;
import com.mostlytricks.study.web.dto.PostsResponseDto;
import com.mostlytricks.study.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save (PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsSaveRequestDto requestDto){

        Posts posts = postsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글 없음 -> id = "+id)
        );
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        //service 계층에서 response-dto만들어둠유의할 것. 조회결과가 없는 경우 핸들링도 여기서 담당함.
        Posts entity = postsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글 없음 -> id = "+id)
        );
        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findALLDesc(){
        return postsRepository.findALLDesc()
                .stream()
                .map(PostsListResponseDto::new)// posts-> new PostsListResponseDto(posts), java lambda func.
                .collect(Collectors.toList());
    }
}
