package com.project.board.service;

import com.project.board.domain.Member;
import com.project.board.domain.Post;
import com.project.board.dto.PostFormDto;
import com.project.board.dto.ResPostDto;
import com.project.board.repository.MemberRepository;
import com.project.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 게시글 등록
    @Transactional
    public Long savePost(Long userId, PostFormDto postFormDto){
        Member member = memberRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        Post post = Post.builder()
                .title(postFormDto.getTitle())
                .content(postFormDto.getContent())
                .member(member)
                .build();

        postRepository.save(post);

        return post.getId();
    }

    // 게시글 수정
    @Transactional
    public Long updatePost(Long postId, PostFormDto postFormDto){
        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        post.update(postFormDto.getTitle(), postFormDto.getContent());

        return postId;
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

    // 게시글 조회
    public ResPostDto findPostById(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        return new ResPostDto(post.getId(), post.getTitle(), post.getContent());
    }

}
