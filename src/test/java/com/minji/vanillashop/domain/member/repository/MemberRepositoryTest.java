package com.minji.vanillashop.domain.member.repository;

import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.member.entity.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class MemberRepositoryTest {

    private final String name = "minji";
    private final String password = "Minji1";

    @Autowired
    private MemberRepository memberRepository;


    @DisplayName("사용자 저장 테스트")
    @Test
    public void 사용자_저장() {

        IntStream.rangeClosed(1, 20).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@minji.com")
                    .name("사용자" + i)
                    .password(password)
                    .build();

            memberRepository.save(member);

        });
    }
}