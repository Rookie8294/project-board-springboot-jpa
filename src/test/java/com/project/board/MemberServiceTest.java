package com.project.board;


import com.project.board.domain.Address;
import com.project.board.domain.Gender;
import com.project.board.domain.Member;
import com.project.board.domain.Role;
import com.project.board.dto.MemberDto;
import com.project.board.dto.ResMemberDto;
import com.project.board.repository.MemberRepository;
import com.project.board.service.MemberService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MemberServiceTest {

    @Autowired private MemberService memberService;
    @Autowired private MemberRepository memberRepository;
    @Autowired private EntityManager em;

    @Test
    @DisplayName("멤버 회원가입")
    public void joinMember() throws Exception {
        //given
//        MemberDto memberDto = MemberDto.builder()
//                .name("Jang")
//                .email("test@test.com")
//                .gender(Gender.M)
//                .city("city")
//                .street("street")
//                .zipcode("zipcode")
//                .password("test1234")
//                .build();
//
//        memberService.join(memberDto);
//
//        em.flush();

        Member member = memberRepository.findByEmail("test@test.com");
        System.out.println(member.getEmail());


        //when
//        Long memberId = memberService.join(memberDto);
//        ResMemberDto findMember = memberService.findMemberById(memberId);

        //then
//        Assertions.assertEquals(findMember.getId(), memberId);
//        Assertions.assertEquals(findMember.getName(), "Jang");

    }


}
