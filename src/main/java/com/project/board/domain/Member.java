package com.project.board.domain;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String password;
    private String email;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String email, String password, String name, Address address, Gender gender, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.role = role;
        this.setCreateTime(LocalDateTime.now());
    }
}

