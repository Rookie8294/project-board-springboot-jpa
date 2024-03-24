package com.project.board.dto;

import com.project.board.domain.Address;
import com.project.board.domain.Gender;
import com.project.board.domain.Role;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResMemberDto {

    private Long id;
    private String email;
    private String password;
    private String name;
    private Address address;
    private Gender gender;
    private Role role;
    private LocalDateTime time;

    @Builder
    public ResMemberDto(Long id, String email, String password, String name, Address address, Gender gender, Role role, LocalDateTime time) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.role = role;
        this.time = time;
    }
}
