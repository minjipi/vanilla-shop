package com.minji.vanillashop.domain.member.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberTest {

    private final String email = "ghdalswl77@naver.com";
    private final String name = "minji";
    private final String password = "Minji1";


    @Nested
    @DisplayName("사용자 entity 생성 검증")
    class ValidationPostMemberDto {

        @Nested
        @DisplayName("정상 케이스")
        class SuccessCase {
            @Test
            @DisplayName("정상 값이면 entity 생성 성공")
            void createMemberEntity() {
                //given, when
                Member member = Member.builder()
                        .email(email)
                        .name(name)
                        .password(password)
                        .build();

                //then
                assertThat(member.getEmail()).isEqualTo(email);
                assertThat(member.getName()).isEqualTo(name);
                assertThat(member.getPassword()).isEqualTo(password);
            }
        }

        @Nested
        @DisplayName("비정상 케이스")
        class FailCase {
            @Test
            @DisplayName("이름이 없으면 entity 생성에 실패한다.")
            void createMemberEntityFailByNameIsNull() {
                //given, when
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                        Member.builder()
                                .email(email)
                                .password(password)
                                .build()
                );

                //then
                assertThat(exception.getMessage()).isEqualTo("이름은 필수값 입니다.");
            }
        }
    }
}