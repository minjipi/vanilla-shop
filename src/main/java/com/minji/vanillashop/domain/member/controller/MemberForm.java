package com.minji.vanillashop.domain.member.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter

public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 정보 입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 정보 입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 정보 입니다.")
    private String password;

}
