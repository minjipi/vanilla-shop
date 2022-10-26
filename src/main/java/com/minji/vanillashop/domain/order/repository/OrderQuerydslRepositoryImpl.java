//package com.minji.vanillashop.domain.order.repository;
//
//import com.minji.vanillashop.domain.order.dto.domain.OrderDetail;
//import com.minji.vanillashop.domain.order.entity.Order;
//import com.minji.vanillashop.domain.order.entity.OrderSearch;
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.util.StringUtils;
//
//import javax.persistence.TypedQuery;
//import java.util.List;
//
//@RequiredArgsConstructor
//public class OrderQuerydslRepositoryImpl implements OrderQuerydslRepository {
//    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public List<OrderDetail> findOrderDetailByMemberEmail(String memberEmail) {
//        return jpaQueryFactory
//                .select(Projections.constructor(OrderDetail.class,
//                        order.orderNo,
//                        order.productName,
//                        order.orderAt
//                        )
//                )
//                .from(order)
//                .innerJoin(order.member, member)
//                .where(condition(memberEmail, member.email::eq))
//                .fetch();
//    }
//
////    public List<Order> findAllByString(OrderSearch orderSearch) {
////
////        String jpql = "select o from Order o join o.member m";
////        boolean isFirstCondition = true;
////
////        //주문 상태 검색
////        if (orderSearch.getOrderStatus() != null) {
////            if (isFirstCondition) {
////                jpql += " where";
////                isFirstCondition = false;
////            } else {
////                jpql += " and";
////            }
////            jpql += " o.status = :status";
////        }
////
////        //회원 이름 검색
////        if (StringUtils.hasText(orderSearch.getMemberName())) {
////            if (isFirstCondition) {
////                jpql += " where";
////                isFirstCondition = false;
////            } else {
////                jpql += " and";
////            }
////            jpql += " m.name like :name";
////        }
////
////        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
////                .setMaxResults(1000);
////
////        if (orderSearch.getOrderStatus() != null) {
////            query = query.setParameter("status", orderSearch.getOrderStatus());
////        }
////        if (StringUtils.hasText(orderSearch.getMemberName())) {
////            query = query.setParameter("name", orderSearch.getMemberName());
////        }
////
////        return query.getResultList();
////    }
////
//
////    public List<Order> findAllWithMemberDelivery(int offset, int limit) {
////        return em.createQuery(
////                "select o from Order o" +
////                        " join fetch o.member m" +
////                        " join fetch o.delivery d", Order.class)
////                .setFirstResult(offset)
////                .setMaxResults(limit)
////                .getResultList();
////    }
////
////    public List<Order> findAllWithProduct() {
////        return em.createQuery(
////                "select distinct o from Order o" +
////                        " join fetch o.member m" +
////                        " join fetch o.delivery d" +
////                        " join fetch o.orderItems oi" +
////                        " join fetch oi.product p", Order.class)
////                .setFirstResult(1)
////                .setMaxResults(10)
////                .getResultList();
////    }
//}