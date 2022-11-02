package com.minji.vanillashop.domain.member.repository;

import com.minji.vanillashop.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
    Optional<Member> findById(Long memberId);

}
