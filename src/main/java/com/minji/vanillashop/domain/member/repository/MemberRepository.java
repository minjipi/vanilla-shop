package com.minji.vanillashop.domain.member.repository;

import com.minji.vanillashop.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    boolean existsByEmail(String email);
    Optional<Member> findById(Long memberId);

}
