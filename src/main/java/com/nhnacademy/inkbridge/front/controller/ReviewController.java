package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.review.ReviewCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.review.ReviewReadResponseDto;
import com.nhnacademy.inkbridge.front.service.FileService;
import com.nhnacademy.inkbridge.front.service.ReviewService;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/mypage/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 조회, 등록, 수정
    @GetMapping
    public String getReviews(Model model) {
        Long memberId = CommonUtils.getMemberId();
        ReviewReadResponseDto reviews = reviewService.getReviews(memberId);
        model.addAttribute("reviews", reviews.getReviewDetailReadResponseDtos());
        model.addAttribute("reviewFiles", reviews.getReviewFiles());

        return "member/review";
    }

    @PostMapping
    public String createReview(
        @RequestParam(name = "image", required = false) MultipartFile[] reviewFiles,
        @ModelAttribute ReviewCreateRequestDto reviewCreateRequestDto,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException();
        }
        Long memberId = CommonUtils.getMemberId();

        log.info("here1");

        reviewService.createReview(reviewFiles, memberId, reviewCreateRequestDto);
        log.info("here1");
        // TODO: 결제 내역 페이지로 redirect하도록 수정
        return "redirect:/mypage/review";
    }
}
