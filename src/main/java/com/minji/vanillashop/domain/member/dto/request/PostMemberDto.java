package com.minji.vanillashop.domain.member.dto.request;

import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.global.exceptions.RequestValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.regex.Pattern;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostMemberDto {

    @Size(max = 100, message = "이메일을 100글자 이내로 입력해주세요.")
    @NotEmpty(message = "이메일은 필수값 입니다.")
    @Email(message = "올바른 형식의 이메일을 입력해주세요.")
    private String email;

    @Size(max = 20, message = "이름을 20글자 이내로 입력해주세요.")
    @NotEmpty(message = "이름은 필수값 입니다.")
    private String name;

    @Size(min = 10, message = "비밀번호를 10글자 이상으로 입력해주세요.")
    @NotEmpty(message = "비밀번호는 필수값 입니다.")
    private String password;

    public void validation() {
        String nameRule = "^[가-힣a-zA-Z ]*$";
        String nicknameRule = "^[a-z]*$";
        String passwordRule = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{10,}$";
        String phoneRule = "^[0-9]*$";
        String emailRule = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+";

        if (!Pattern.matches(nameRule, name))
            throw new RequestValidationException("이름은 한글, 영문 대소문자만 입력 가능합니다.");

//        if (!Pattern.matches(nicknameRule, nickname))
//            throw new RequestValidationException("별명은 영문 소문자만 입력 가능합니다.");
//
//        if (!Pattern.matches(passwordRule, password))
//            throw new RequestValidationException("비밀번호는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함해야 합니다.");
//
//        if (!Pattern.matches(phoneRule, phone))
//            throw new RequestValidationException("휴대폰번호는 '-' 제외 숫자만 입력해주세요.");
//
//        if (!Pattern.matches(emailRule, email))
//            throw new RequestValidationException("올바른 형식의 이메일을 입력해주세요.");
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
    }

}
