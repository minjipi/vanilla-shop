package com.minji.vanillashop.domain.member.repository;

import com.minji.vanillashop.domain.member.dto.domain.MemberDetailInfo;
import com.minji.vanillashop.domain.member.dto.request.MemberListQuery;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.minji.vanillashop.domain.member.entity.QMember.member;

@RequiredArgsConstructor
@Repository
public class MemberQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<MemberDetailInfo> findOrderDetailByMemberBy(MemberListQuery query) {
        JPAQuery<MemberDetailInfo> jpaQuery = jpaQueryFactory
                .select(Projections.constructor(MemberDetailInfo.class,
                        member.email,
                        member.name,
                        member.modDate
                        )
                )
                .from(member)
                .where(member.email.eq(member.email)

                );

        if (query.getMemberEmail() != null) {
            jpaQuery.where(member.email.eq(query.getMemberEmail()));
        }

        return jpaQuery.fetch();
    }
}
