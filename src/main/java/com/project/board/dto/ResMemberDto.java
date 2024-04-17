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
    private String name;
    private Address address;
    private Gender gender;
    private Role role;
    private String time;

    @Builder
    public ResMemberDto(Long id, String email, String name, Address address, Gender gender, Role role, String time) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.role = role;
        this.time = time;
    }
}
