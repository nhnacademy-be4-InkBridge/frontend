package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.ReviewAdaptor;
import com.nhnacademy.inkbridge.front.dto.review.ReviewBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewMemberReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewUpdateRequestDto;
import com.nhnacademy.inkbridge.front.service.ReviewService;
import java.io.IOException;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewMemberReadResponseDto getReviews(Long memberId, Long size, Long page) {
        return reviewAdaptor.getReviews(memberId, size, page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewBookReadResponseDto getReviewsByBookId(Long bookId) {
        return reviewAdaptor.getReviewsByBookId(bookId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createReview(MultipartFile[] reviewFiles,
        Long memberId, ReviewCreateRequestDto reviewCreateRequestDto) throws IOException {
        reviewAdaptor.createReview(reviewFiles, memberId, reviewCreateRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateReview(MultipartFile[] reviewFiles, Long reviewId, Long memberId,
        ReviewUpdateRequestDto reviewUpdateRequestDto) throws IOException {
        reviewAdaptor.updateReview(memberId, reviewId, reviewUpdateRequestDto, reviewFiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewBookReadResponseDto getReviewPagination(Long page, Long size, Long bookId) {
        return reviewAdaptor.getReviewPagination(page, size, bookId);
    }
}
