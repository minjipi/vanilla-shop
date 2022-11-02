package com.minji.vanillashop.domain.member.dto.request;

import com.minji.vanillashop.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberDto {
    private String name;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .build();
    }
}