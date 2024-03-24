package com.project.board.repository;

import com.project.board.domain.Member;
import com.project.board.dto.ResMemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.email = :email")
    Member findByEmail(String email);

}
