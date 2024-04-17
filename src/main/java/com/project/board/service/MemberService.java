package com.project.board.service;


import com.project.board.auth.MemberDetails;
import com.project.board.domain.Address;
import com.project.board.domain.Member;
import com.project.board.domain.Role;
import com.project.board.dto.MemberDto;
import com.project.board.dto.ResMemberDto;
import com.project.board.dto.UpdateMemberRequest;
import com.project.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 사용자 회원가입
    @Transactional
    public Long join(MemberDto memberDto){
        Member member = Member.builder()
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .address(new Address(memberDto.getCity(), memberDto.getStreet(), memberDto.getZipcode()))
                .gender(memberDto.getGender())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .role(Role.USER)
                .build();

        memberRepository.save(member);

        return member.getId();
    }

    // 사용자 조회
    public ResMemberDto findMemberById(Long id){
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        ResMemberDto resMemberDto =  ResMemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .address(member.getAddress())
                .email(member.getEmail())
                .gender(member.getGender())
                .role(member.getRole())
                .time(member.getModifiedDate())
                .build();

        return resMemberDto;
    }

    // 전체 사용자 조회
    public List<ResMemberDto> findAllMember(){
        List<Member> memberList = memberRepository.findAll();
        List<ResMemberDto> resMemberList = new ArrayList<>();
        for( Member member : memberList ){
            ResMemberDto resMemberDto =  ResMemberDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .address(member.getAddress())
                    .email(member.getEmail())
                    .gender(member.getGender())
                    .role(member.getRole())
                    .time(member.getModifiedDate())
                    .build();

            resMemberList.add(resMemberDto);
        }

        return resMemberList;
    }

    // 회원 정보 수정
    @Transactional
    public void updateMember(Long id, UpdateMemberRequest memberDto){
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        Address address = null;
        if( memberDto.getCity() != null && memberDto.getStreet() != null && memberDto.getZipcode() != null) {
            address = new Address(memberDto.getCity(), memberDto.getStreet(), memberDto.getZipcode());
        }

        member.update(
            memberDto.getEmail() != null? memberDto.getEmail() : member.getEmail(),
            memberDto.getPassword() != null? memberDto.getPassword() : member.getPassword(),
            memberDto.getName() != null? memberDto.getName() : member.getName(),
            address != null? address : member.getAddress()
        );
    }

    // 회원 탈퇴
    @Transactional
    public void deleteMember(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        memberRepository.delete(member);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if( member == null){
            throw new UsernameNotFoundException("Can not find user");
        }

        return new MemberDetails(member);
    }
}
