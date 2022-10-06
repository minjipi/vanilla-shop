package com.minji.vanillashop.domain.member.service;

import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.member.repository.MemberRepository;
import com.minji.vanillashop.global.exceptions.MemberEmailDuplicateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class MemberServiceTest {

    private final String email = "ghdalswl77@naver.com";
    private final String name = "minji";
    private final String password = "Minji1";

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;


    @Nested
    @DisplayName("사용자 생성 테스트")
    class JoinMember {

        @Nested
        @DisplayName("정상 케이스")
        class SuccessCase {

            @Test
            @DisplayName("사용자 생성 성공")
            public void 회원가입() throws Exception {
                //given
                Member member = Member.builder()
                        .name(name)
                        .email(email)
                        .password(password)
                        .build();

                //when
                Long saveID = memberService.joinMember(member);
                Optional optional = memberRepository.findById(saveID);

                //then
                assertEquals(member, optional.get());
            }

            @Nested
            @DisplayName("비정상 케이스")
            class FailCase {
                @Test
                @DisplayName("이미 사용중인 이메일이면 사용자 생성에 실패한다.")

                public void 중복_회원_예외() throws Exception {
                    //given
                    Member member1 = Member.builder()
                            .name(name)
                            .email(email)
                            .password(password)
                            .build();

                    Member member2 = Member.builder()
                            .name("aaa")
                            .email(email)
                            .password(password)
                            .build();

                    //when
                    Long saveID1 = memberService.joinMember(member1);
                    Long saveID2 = memberService.joinMember(member2);

                    try {
                        memberService.joinMember(member1);
                    } catch (IllegalStateException e) {
                        return;
                    }


                    //then
                    MemberEmailDuplicateException exception = assertThrows(MemberEmailDuplicateException.class, () -> memberService.joinMember(member2));

                    //then
                    assertThat(exception.getMessage()).isEqualTo("이미 사용중인 이메일 입니다.");

                }


            }
        }
    }
}