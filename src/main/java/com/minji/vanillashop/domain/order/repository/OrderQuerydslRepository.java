package com.minji.vanillashop.domain.order.repository;

import com.minji.vanillashop.domain.order.dto.domain.MemberOrderDetail;
import com.minji.vanillashop.domain.order.dto.domain.OrderDetail;
import com.minji.vanillashop.domain.order.dto.request.MemberOrderQuery;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.minji.vanillashop.domain.member.entity.QMember.member;
import static com.minji.vanillashop.domain.order.entity.QOrder.order;
import static com.minji.vanillashop.domain.order.entity.QOrderItem.orderItem;
import static com.minji.vanillashop.domain.product.entity.QProduct.product;

@RequiredArgsConstructor
@Repository
public class OrderQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<OrderDetail> findOrderDetailByMemberEmail(String memberEmail){
        return jpaQueryFactory
                .select(Projections.constructor(OrderDetail.class,
                        order.orderNo,
                        orderItem.id,
                        product.title,
                        order.orderDate)
                )
                .from(order)
                .innerJoin(order.orderItems, orderItem)
                .innerJoin(orderItem.product, product)
                .innerJoin(order.member, member)
                .where(member.email.eq(memberEmail))
                .fetch();
    }

    private <T> BooleanExpression condition(T value, Function<T, BooleanExpression> function) {
        return Optional.ofNullable(value).map(function).orElse(null);
    }

    public List<MemberOrderDetail> findMemberOrderDetailListBy(MemberOrderQuery query) {
        JPAQuery<MemberOrderDetail> jpaQuery = jpaQueryFactory
                .select(Projections.constructor(MemberOrderDetail.class,
                        member.email,
                        order.orderDate,
                        order.status
                        )
                )
                .from(member)
                .leftJoin(member.orders, order)
                .where(order.orderDate.eq(JPAExpressions
                                .select(order.orderDate.max())
                                .from(order)
                                .where(order.member.eq(member)
                                )
                        )
                                .or(member.orders.isEmpty())
                );

        if (query.getMemberEmail() != null) {
            jpaQuery.where(member.email.eq(query.getMemberEmail()));
        }

        //페이징 설정시에만 페이징 처리 그 외에는 전부 조회
        if (query.getLimit() != 0) {
            jpaQuery.limit(query.getLimit()).offset(query.getOffset());
        }

        return jpaQuery.fetch();
    }
}
