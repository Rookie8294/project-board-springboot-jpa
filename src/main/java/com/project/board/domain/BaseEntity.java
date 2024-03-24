package com.project.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Column(name = "CREATE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

}
