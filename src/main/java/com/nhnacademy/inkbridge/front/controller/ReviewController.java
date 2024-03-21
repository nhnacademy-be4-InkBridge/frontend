package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.review.ReviewBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewMemberReadResponseDto;
import com.nhnacademy.inkbridge.front.exception.ValidationException;
import com.nhnacademy.inkbridge.front.service.ReviewService;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String getReviews(Model model) {
        Long memberId = CommonUtils.getMemberId();
        ReviewMemberReadResponseDto reviews = reviewService.getReviews(memberId);
        model.addAttribute("reviews", reviews.getReviewDetailReadResponseDtos());
        model.addAttribute("reviewFiles", reviews.getReviewFiles());

        return "member/review";
    }

    /**
     * 리뷰를 등록하는 메서드입니다.
     *
     * @param reviewFiles MultipartFile[]
     * @param reviewCreateRequestDto ReviewCreateRequestDto
     * @param bindingResult BindingResult
     * @return 결제 내역 페이지
     */
    @PostMapping("/mypage/review")
    public String createReview(
        @RequestParam(name = "image", required = false) MultipartFile[] reviewFiles,
        @ModelAttribute ReviewCreateRequestDto reviewCreateRequestDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        Long memberId = CommonUtils.getMemberId();

        try {
            reviewService.createReview(reviewFiles, memberId, reviewCreateRequestDto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // TODO: 결제 내역 페이지로 redirect하도록 수정
        return "redirect:/mypage/review";
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
