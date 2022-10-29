package com.minji.vanillashop.domain.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MemberOrderQuery {
    private String memberEmail;
    private int offset;
    private int limit;
}
