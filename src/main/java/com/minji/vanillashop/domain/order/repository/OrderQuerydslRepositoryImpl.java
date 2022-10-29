package com.minji.vanillashop.domain.order.repository;

import com.minji.vanillashop.domain.order.dto.domain.MemberOrderDetail;
import com.minji.vanillashop.domain.order.dto.request.MemberOrderQuery;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.minji.vanillashop.domain.member.entity.QMember.member;
import static com.minji.vanillashop.domain.order.entity.QOrder.order;


@RequiredArgsConstructor
public class OrderQuerydslRepositoryImpl implements OrderQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
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
            System.out.println("====== 페이징 설정시에만 페이징 처리 그 외에는 전부 조회 ");
        }

        return jpaQuery.fetch();
    }


//    public List<Order> findAllByString(OrderSearch orderSearch) {
//
//        String jpql = "select o from Order o join o.member m";
//        boolean isFirstCondition = true;
//
//        //주문 상태 검색
//        if (orderSearch.getOrderStatus() != null) {
//            if (isFirstCondition) {
//                jpql += " where";
//                isFirstCondition = false;
//            } else {
//                jpql += " and";
//            }
//            jpql += " o.status = :status";
//        }
//
//        //회원 이름 검색
//        if (StringUtils.hasText(orderSearch.getMemberName())) {
//            if (isFirstCondition) {
//                jpql += " where";
//                isFirstCondition = false;
//            } else {
//                jpql += " and";
//            }
//            jpql += " m.name like :name";
//        }
//
//        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
//                .setMaxResults(1000);
//
//        if (orderSearch.getOrderStatus() != null) {
//            query = query.setParameter("status", orderSearch.getOrderStatus());
//        }
//        if (StringUtils.hasText(orderSearch.getMemberName())) {
//            query = query.setParameter("name", orderSearch.getMemberName());
//        }
//
//        return query.getResultList();
//    }
//

//    public List<Order> findAllWithMemberDelivery(int offset, int limit) {
//        return em.createQuery(
//                "select o from Order o" +
//                        " join fetch o.member m" +
//                        " join fetch o.delivery d", Order.class)
//                .setFirstResult(offset)
//                .setMaxResults(limit)
//                .getResultList();
//    }
//
//    public List<Order> findAllWithProduct() {
//        return em.createQuery(
//                "select distinct o from Order o" +
//                        " join fetch o.member m" +
//                        " join fetch o.delivery d" +
//                        " join fetch o.orderItems oi" +
//                        " join fetch oi.product p", Order.class)
//                .setFirstResult(1)
//                .setMaxResults(10)
//                .getResultList();
//    }

}