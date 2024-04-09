package com.project.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResPostDto {

    Long id;
    Long userId;
    String title;
    String content;
    String author;
    String postTime;

    @Builder
    public ResPostDto(Long id, String title, String content, String author, String postTime, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.postTime = postTime;
        this.userId = userId;
    }

}
