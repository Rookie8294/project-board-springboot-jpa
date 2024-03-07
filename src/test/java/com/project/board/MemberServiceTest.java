package com.project.board;


import com.project.board.domain.Address;
import com.project.board.domain.Gender;
import com.project.board.dto.MemberDto;
import com.project.board.dto.ResMemberDto;
import com.project.board.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MemberServiceTest {

    @Autowired private MemberService memberService;

    @Test
    @DisplayName("멤버 회원가입")
    public void joinMember() throws Exception {
        //given
        MemberDto memberDto = MemberDto.builder()
                .name("Jang")
                .email("test@test.com")
                .gender(Gender.M)
                .address(new Address("city","street","zipcode"))
                .password("test1234")
                .build();


        //when
        Long memberId = memberService.join(memberDto);
        ResMemberDto findMember = memberService.findMemberById(memberId);


        //then
        Assertions.assertEquals(findMember.getId(), memberId);
        Assertions.assertEquals(findMember.getName(), "Jang");

    }


}
