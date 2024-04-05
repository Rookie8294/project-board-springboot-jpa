package com.project.board;

import com.project.board.domain.Address;
import com.project.board.domain.Gender;
import com.project.board.dto.MemberDto;
import com.project.board.dto.PostFormDto;
import com.project.board.dto.ResPostDto;
import com.project.board.repository.MemberRepository;
import com.project.board.service.MemberService;
import com.project.board.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired PostService postService;

    @Test
    @DisplayName("게시글 등록")
    void savePost() {
        //given
        // 회원 생성
        Long memberId = createMember();
        // 게시글 생성
        PostFormDto postFormDto = new PostFormDto("originalTitle", "originalContent");

        //when
        // 게시글 등록
        Long postId = postService.savePost(postFormDto,memberId);

        //then
        // 등록된 게시글 조회
        ResPostDto resPostDto = postService.findPostById(postId);
        Assertions.assertEquals(resPostDto.getId(), postId);
        Assertions.assertEquals(resPostDto.getTitle(), "originalTitle");
        Assertions.assertEquals(resPostDto.getContent(), "originalContent");
    }

    @Test
    @DisplayName("게시글 수정")
    public void updatePost() {
        //given
        // 회원 생성
        Long memberId = createMember();
        // 게시글 생성
        PostFormDto postFormDto = new PostFormDto("originalTitle", "originalContent");
        // 게시글 등록
        Long postId = postService.savePost(postFormDto, memberId);
        // 수정할 게시글 생성
        PostFormDto modifiedPostFormDto = new PostFormDto("modifiedTitle", "modifiedContent");

        //when
        // 게시글 수정
        postService.updatePost(modifiedPostFormDto, postId);

        //then
        // 수정 후 게시글 조회
        ResPostDto resPostDto = postService.findPostById(postId);
        Assertions.assertNotEquals("originalTitle", resPostDto.getTitle());
        Assertions.assertNotEquals("originalContent", resPostDto.getContent());
        Assertions.assertEquals("modifiedTitle", resPostDto.getTitle());
        Assertions.assertEquals("modifiedContent", resPostDto.getContent());

    }

    @Test
    @DisplayName("게시글 삭제")
    public void deletePost() {
        //when
        // 회원 생성
        Long memberId = createMember();
        // 게시글 생성
        PostFormDto postFormDto = new PostFormDto("originalTitle", "originalContent");
        // 게시글 등록
        Long postId = postService.savePost(postFormDto, memberId);

        //when
        // 게시글 삭제
        postService.deletePost(postId);

        //then
        // 삭제된 게시글 조회
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            postService.findPostById(postId);
        });

    }

    public Long createMember(){
        MemberDto memberDto = MemberDto.builder()
                .name("test")
                .email("test@test.com")
                .gender(Gender.M)
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .password("test1234")
                .build();

        return memberService.join(memberDto);
    }


}
