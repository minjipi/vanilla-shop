package com.minji.vanillashop.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostOrderDto {
    private Long memberId;
    private Long productId;
    private Integer count;
}
