package com.minji.vanillashop.domain.review.controller;

import com.minji.vanillashop.domain.review.dto.ReviewDTO;
import com.minji.vanillashop.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{pno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("pno") Long pno){
        log.info("--------------list---------------");
        log.info("PNO: " + pno);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfProduct(pno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{pno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO productReviewDTO){
        log.info("--------------add ProductReview---------------");
        log.info("reviewDTO: " + productReviewDTO);

        Long reviewnum = reviewService.register(productReviewDTO);

        return new ResponseEntity<>( reviewnum, HttpStatus.OK);
    }

    @PutMapping("/{pno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum,
                                             @RequestBody ReviewDTO productReviewDTO){
        log.info("---------------modify ProductReview--------------" + reviewnum);
        log.info("reviewDTO: " + productReviewDTO);

        reviewService.modify(productReviewDTO);

        return new ResponseEntity<>( reviewnum, HttpStatus.OK);
    }

    @DeleteMapping("/{pno}/{reviewnum}")
    public ResponseEntity<Long> removeReview( @PathVariable Long reviewnum){
        log.info("---------------modify removeReview--------------");
        log.info("reviewnum: " + reviewnum);

        reviewService.remove(reviewnum);

        return new ResponseEntity<>( reviewnum, HttpStatus.OK);
    }

}

