package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.review.ReviewCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewReadResponseDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: ReviewService.
 *
 * @author minm063
 * @version 2024/03/19
 */
public interface ReviewService {

    ReviewReadResponseDto getReviews(Long memberId);

    ReviewReadResponseDto getReviewsByBookId(Long bookId);

    void createReview(MultipartFile[] reviewFiles, Long memberId,
        ReviewCreateRequestDto reviewCreateRequestDto);
}
