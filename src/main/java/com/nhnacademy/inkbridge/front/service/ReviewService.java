package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.review.ReviewBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewMemberReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewUpdateRequestDto;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: ReviewService.
 *
 * @author minm063
 * @version 2024/03/19
 */
public interface ReviewService {

    /**
     * 회원 번호로 전체 리뷰를 조회하는 메서드입니다.
     *
     * @param memberId Long
     * @param size Long
     * @param page Long
     * @return ReviewMemberReadResponseDto
     */
    ReviewMemberReadResponseDto getReviews(Long memberId, Long size, Long page);

    /**
     * 도서 번호로 전체 리뷰를 조회하는 메서드입니다.
     *
     * @param bookId Long
     * @return ReviewBookReadResponseDto
     */
    ReviewBookReadResponseDto getReviewsByBookId(Long bookId);

    /**
     * 리뷰를 등록하는 메서드입니다.
     *
     * @param reviewFiles MultipartFile[]
     * @param memberId Long
     * @param reviewCreateRequestDto ReviewCreateRequestDto
     */
    void createReview(MultipartFile[] reviewFiles, Long memberId,
        ReviewCreateRequestDto reviewCreateRequestDto) throws IOException;

    /**
     * 리뷰를 수정하는 메서드입니다.
     *
     * @param reviewFiles MultipartFile
     * @param reviewId Long
     * @param memberId Long
     * @param reviewUpdateRequestDto ReviewCreateRequestDto
     */
    void updateReview(MultipartFile[] reviewFiles, Long reviewId, Long memberId,
        ReviewUpdateRequestDto reviewUpdateRequestDto) throws IOException;

    /**
     * 도서 번호로 페이징처리된 리뷰를 조회하는 메서드입니다.
     *
     * @param page Long
     * @param size Long
     * @param bookId Long
     * @return ReviewBookReadResponseDto
     */
    ReviewBookReadResponseDto getReviewPagination(Long page, Long size, Long bookId);
}
