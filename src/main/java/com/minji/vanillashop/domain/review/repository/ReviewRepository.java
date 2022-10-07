package com.minji.vanillashop.domain.review.repository;

import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.product.entity.Product;
import com.minji.vanillashop.domain.review.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    @Modifying
//    @Query("delete from Review mr where mr.member = :member")
//    void deleteByMember(Member member);

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByProduct(Product product);

    @Modifying
    @Query("delete from Review pr where pr.member = :member")
    void deleteByMember(Member member);


}
