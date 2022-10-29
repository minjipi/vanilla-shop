package com.minji.vanillashop.domain.order.repository;

import com.minji.vanillashop.domain.order.dto.domain.MemberOrderDetail;
import com.minji.vanillashop.domain.order.dto.request.MemberOrderQuery;

import java.util.List;

public interface OrderQuerydslRepository {

    List<MemberOrderDetail> findMemberOrderDetailListBy(MemberOrderQuery query);
}
