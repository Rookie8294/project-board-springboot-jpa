package com.project.board.service;


import com.project.board.auth.MemberDetails;
import com.project.board.domain.Address;
import com.project.board.domain.Member;
import com.project.board.domain.Role;
import com.project.board.dto.MemberDto;
import com.project.board.dto.ResMemberDto;
import com.project.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if( member == null ){
            throw new UsernameNotFoundException(email);
        }

        return new MemberDetails(member);
    }
}
