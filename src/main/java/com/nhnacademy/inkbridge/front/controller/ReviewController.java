package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.review.ReviewBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewMemberReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewUpdateRequestDto;
import com.nhnacademy.inkbridge.front.exception.ValidationException;
import com.nhnacademy.inkbridge.front.service.ReviewService;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.io.IOException;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * class: ReviewController.
 *
 * @author minm063
 * @version 2024/03/19
 */
@Slf4j
@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private static final String REDIRECT_REVIEW = "redirect:/mypage/review";

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * 회원 번호로 전체 리뷰 목록을 조회합니다.
     *
     * @param model Model
     * @return 마이페이지의 작성한 리뷰 조회 페이지
     */
    @GetMapping("/mypage/review")
    public String getReviews(Model model,
        @RequestParam(value = "size", defaultValue = "5") Long size,
        @RequestParam(value = "page", defaultValue = "0") Long page) {
        Long memberId = CommonUtils.getMemberId();
        ReviewMemberReadResponseDto reviews = reviewService.getReviews(memberId, size, page);
        model.addAttribute("reviews", reviews.getReviewDetailReadResponseDtos());
        model.addAttribute("reviewFiles", reviews.getReviewFiles());
        model.addAttribute("reviewCount", reviews.getCount());

        return "member/review";
    }

    /**
     * 리뷰를 등록하는 메서드입니다.
     *
     * @param reviewFiles MultipartFile[]
     * @param reviewCreateRequestDto ReviewCreateRequestDto
     * @param bindingResult BindingResult
     * @return 리뷰 목록 페이지
     */
    @PostMapping("/mypage/review")
    public String createReview(
        @RequestParam(name = "image", required = false) MultipartFile[] reviewFiles,
        @Valid @ModelAttribute ReviewCreateRequestDto reviewCreateRequestDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        Long memberId = CommonUtils.getMemberId();

        try {
            reviewService.createReview(reviewFiles, memberId, reviewCreateRequestDto);
        } catch (IOException e) {
            return REDIRECT_REVIEW;
        }

        return REDIRECT_REVIEW;
    }

    /**
     * 리뷰를 수정하는 메서드입니다.
     *
     * @param reviewFiles MultipartFile[]
     * @param reviewId Long
     * @param reviewUpdateRequestDto ReviewUpdateRequestDto
     * @param bindingResult BindingResult
     * @return 리뷰 목록 페이지
     */
    @PostMapping("/mypage/review/{reviewId}")
    public String updateReview(
        @RequestParam(name = "image", required = false) MultipartFile[] reviewFiles,
        @PathVariable(name = "reviewId") Long reviewId,
        @Valid @ModelAttribute ReviewUpdateRequestDto reviewUpdateRequestDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        Long memberId = CommonUtils.getMemberId();

        try {
            reviewService.updateReview(reviewFiles, reviewId, memberId, reviewUpdateRequestDto);
        } catch (IOException e) {
            return REDIRECT_REVIEW;
        }

        return REDIRECT_REVIEW;
    }

    /**
     * 도서 번호로 리뷰 목록을 조회합니다.
     *
     * @param model Model
     * @param page Long, default = 0
     * @param size Long, default = 10
     * @param bookId Long
     * @return 리뷰 목록
     */
    @GetMapping("/review")
    public String getReviewPagination(Model model,
        @RequestParam(value = "page", defaultValue = "0") Long page,
        @RequestParam(value = "size", defaultValue = "5") Long size,
        @RequestParam("bookId") Long bookId) {
        ReviewBookReadResponseDto reviews = reviewService.getReviewPagination(page, size, bookId);
        model.addAttribute("reviews", reviews.getReviewDetailReadResponseDtos());
        model.addAttribute("reviewFiles", reviews.getReviewFiles());

        return "main/review_chunk";
    }
}
