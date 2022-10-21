package com.minji.vanillashop.domain.review.service;


import com.minji.vanillashop.domain.product.entity.Product;
import com.minji.vanillashop.domain.review.Review;
import com.minji.vanillashop.domain.review.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    //영화의 모든 영화리뷰를 가져온다.
    List<ReviewDTO> getListOfProduct(Long pno);

    //영화 리뷰를 추가
    Long register(ReviewDTO productReviewDTO);

    //특정한 영화리뷰 수정
    void modify(ReviewDTO productReviewDTO);

    //영화 리뷰 삭제
    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO productReviewDTO){

        Review productReview = Review.builder()
                .reviewnum(productReviewDTO.getReviewnum())
                .product(Product.builder().pno(productReviewDTO.getPno()).build())
//                .member(Member.builder().mid(productReviewDTO.getMid()).build())
                .grade(productReviewDTO.getGrade())
                .text(productReviewDTO.getText())
                .build();

        return productReview;
    }

    default ReviewDTO entityToDto(Review productReview){

        ReviewDTO productReviewDTO = ReviewDTO.builder()
                .reviewnum(productReview.getReviewnum())
                .pno(productReview.getProduct().getPno())
//                .mid(productReview.getMember().getMid())
                .name(productReview.getMember().getName())
                .email(productReview.getMember().getEmail())
                .grade(productReview.getGrade())
                .text(productReview.getText())
//                .regDate(productReview.getRegDate())
//                .modDate(productReview.getModDate())
                .build();

        return productReviewDTO;
    }
}
