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

    @Builder
    public Member(String email, String password, String name, Address address, Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.setCreateTime(LocalDateTime.now());
    }
}

