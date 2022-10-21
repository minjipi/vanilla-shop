package com.minji.vanillashop.domain.review.dto;

import com.minji.vanillashop.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    //review num
    private Long reviewnum;

    //Movie pno
    private Long pno;

    //Membmer id
    private Long mid;

    Member nickname;

    private String name;
    //Member email
    private String email;

    private int grade;

    private String text;

//    private LocalDateTime regDate, modDate;


}
