package com.project.board.domain;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String password;

    private String name;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}

