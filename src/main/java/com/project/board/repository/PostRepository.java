package com.project.board.repository;

import com.project.board.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface PostRepository extends JpaRepository<Post, Long> {

}
