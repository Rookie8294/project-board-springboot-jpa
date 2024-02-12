package com.project.board.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Post extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
