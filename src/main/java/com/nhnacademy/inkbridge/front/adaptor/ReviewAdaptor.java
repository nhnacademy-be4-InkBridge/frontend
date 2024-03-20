package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.review.ReviewCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewReadResponseDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: ReviewAdaptor.
 *
 * @author minm063
 * @version 2024/03/19
 */
public interface ReviewAdaptor {

    ReviewReadResponseDto getReviews(Long memberId);

    ReviewReadResponseDto getReviewsByBookId(Long bookId);

    void createReview(MultipartFile[] reviewFiles,
        Long memberId, ReviewCreateRequestDto reviewCreateRequestDto);

    void updateReview(Long memberId, Long reviewId, ReviewCreateRequestDto reviewCreateRequestDto,
        List<MultipartFile> reviewFiles);
}
