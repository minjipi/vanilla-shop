package com.minji.vanillashop.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MemberListQuery {
    private String memberEmail;
    private int offset;
    private int limit;
}
