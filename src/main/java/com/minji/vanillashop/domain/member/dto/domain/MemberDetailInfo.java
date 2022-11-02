package com.minji.vanillashop.domain.member.dto.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MemberDetailInfo {
    private String email;
    private String name;
    private LocalDateTime modDate;

}
