package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.coupon.CouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.CouponService;
import javax.validation.Valid;
import javax.validation.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: CouponController.
 *
 * @author JBum
 * @version 2024/02/22
 */


@Controller
@RequestMapping("/admin/coupons")
public class AdminCouponController {

    private final CouponService couponService;

    /**
     * 쿠폰컨트롤러에 필요한 서비스 등록.
     *
     * @param couponService 쿠폰서비스
     */
    public AdminCouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    /**
     * 관리자용 쿠폰리스트 호출하는 메소드.
     *
     * @param couponStatusId 쿠폰상태
     * @param page           페이지
     * @param size           사이즈
     * @param model          html로 넘길 객체
     * @return html주소
     */
    @GetMapping
    public String adminViews(
        @RequestParam(name = "coupon-status-id", defaultValue = "1") Integer couponStatusId,
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size, Model model) {
        model.addAttribute("couponStatusId", couponStatusId);
        model.addAttribute("page", couponService.getAdminCoupons(couponStatusId, page, size));
        return "admin/coupon_list";
    }

    @GetMapping("/register")
    public String createCouponView() {
        return "admin/coupon_create";
    }

    @GetMapping("/category/register")
    public String createCategoryCouponView() {
        return "admin/coupon_create";
    }

    @GetMapping("/book/register")
    public String createBookCouponView() {
        return "admin/coupon_create";
    }

    @PostMapping("/register")
    public String createCoupon(
        @Valid @ModelAttribute CouponCreateRequestDto couponCreateRequestDto) {
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        couponService.createCoupon(couponCreateRequestDto);
        return "redirect:/admin/coupons";
    }

//    @GetMapping("/{couponId}")
//    public String viewAdminCouponDetail(@PathVariable("couponId") String couponId) {
//        CouponReadResponseDto couponService.getCoupon(couponId);
//    }
}
