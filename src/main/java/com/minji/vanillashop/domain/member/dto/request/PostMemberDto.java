package com.minji.vanillashop.domain.member.dto.request;

import com.minji.vanillashop.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
    }

}
