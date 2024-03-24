package com.project.board.dto;

import com.project.board.domain.Address;
import com.project.board.domain.Gender;
import com.project.board.domain.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {

    @Email
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
    
    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;
    
    @NotEmpty(message = "주소를 입력해주세요.")
    private String city;
    @NotEmpty(message = "주소를 입력해주세요.")
    private String street;
    @NotEmpty(message = "주소를 입력해주세요.")
    private String zipcode;
    @NotNull(message = "성별을 선택해주세요.")
    private Gender gender;


    @Builder
    public MemberDto(String email, String password, String name, String city, String street, String zipcode, Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.gender = gender;
    }

}

