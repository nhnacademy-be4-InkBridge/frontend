package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.ReviewAdaptor;
import com.nhnacademy.inkbridge.front.dto.review.ReviewCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewReadResponseDto;
import com.nhnacademy.inkbridge.front.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: ReviewServiceImpl.
 *
 * @author minm063
 * @version 2024/03/19
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewAdaptor reviewAdaptor;

    public ReviewServiceImpl(ReviewAdaptor reviewAdaptor) {
        this.reviewAdaptor = reviewAdaptor;
    }

    @Override
    public ReviewReadResponseDto getReviews(Long memberId) {
        return reviewAdaptor.getReviews(memberId);
    }

    @Override
    public ReviewReadResponseDto getReviewsByBookId(Long bookId) {
        return reviewAdaptor.getReviewsByBookId(bookId);
    }

    @Override
    public void createReview(MultipartFile[] reviewFiles,
        Long memberId, ReviewCreateRequestDto reviewCreateRequestDto) {
        reviewAdaptor.createReview(reviewFiles, memberId, reviewCreateRequestDto);
    }
}
