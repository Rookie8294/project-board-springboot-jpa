package com.project.board.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
public class PostFormDto {

    @NotEmpty(message = "제목을 입력해주세요.")
    String title;
    @NotEmpty(message = "게시글을 작성해주세요.")
    String content;

    @Builder
    public PostFormDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
