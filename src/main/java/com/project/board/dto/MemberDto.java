package com.project.board.dto;

import com.project.board.domain.Address;
import com.project.board.domain.Gender;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {

    @Email
    private String email;

    private String password;
    private String name;
    private Address address;
    private Gender gender;

    @Builder
    public MemberDto(String email, String password, String name, Address address, Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.gender = gender;
    }

}
