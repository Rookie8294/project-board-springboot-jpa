package com.project.board.repository;

import com.project.board.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.member.id = :id")
    Page<Post> findByUserId(Pageable pageable, Long id);

    @Query("select p from Post p join fetch p.member")
    Page<Post> findALl(Pageable pageable);

    @Query("select p from Post p join fetch p.member")
    List<Post> findAllPostList();

    @Query("select p from Post p join fetch p.member pm where p.id = :id")
    Post findOnePostById(Long id);


}
