package com.minji.vanillashop.domain.review.service;

import com.minji.vanillashop.domain.product.entity.Product;
import com.minji.vanillashop.domain.review.Review;
import com.minji.vanillashop.domain.review.dto.ReviewDTO;
import com.minji.vanillashop.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;


    @Override
    public List<ReviewDTO> getListOfProduct(Long pno){

        Product product = Product.builder().pno(pno).build();

        List<Review> result = reviewRepository.findByProduct(product);

        return result.stream().map(productReview -> entityToDto(productReview)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO productReviewDTO) {

        Review productReview = dtoToEntity(productReviewDTO);

        reviewRepository.save(productReview);

        return productReview.getReviewnum();
    }

    @Override
    public void modify(ReviewDTO productReviewDTO) {

        Optional<Review> result =
                reviewRepository.findById(productReviewDTO.getReviewnum());

        if(result.isPresent()){

            Review productReview = result.get();
            productReview.changeGrade(productReviewDTO.getGrade());
            productReview.changeText(productReviewDTO.getText());

            reviewRepository.save(productReview);
        }

    }

    @Override
    public void remove(Long reviewnum) {

        reviewRepository.deleteById(reviewnum);

    }
}

