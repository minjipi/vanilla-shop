package com.minji.vanillashop.domain.review;

import com.minji.vanillashop.domain.member.entity.BaseEntity;
import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.product.entity.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"product", "member"})
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private int grade;

    private String text;

    public void changeGrade(int grade) {
        this.grade = grade;
    }

    public void changeText(String text) {
        this.text = text;
    }

}
