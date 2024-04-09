package com.project.board.repository;

import com.project.board.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.member.id = :id")
    Page<Post> findByUserId(Pageable pageable, Long id);

}
