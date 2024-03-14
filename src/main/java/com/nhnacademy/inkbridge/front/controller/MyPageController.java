package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.service.CouponService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: MyPageController.
 *
 * @author JBum
 * @version 2024/03/12
 */
@Controller
@RequestMapping("/my-page")
public class MyPageController {

    private final CouponService couponService;

    public MyPageController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/coupons")
    public String MyIssuedCouponPage(
        @RequestParam(name = "coupon-status-id", defaultValue = "1") Integer couponStatusId,
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size, Model model) {
        String memberId = (String) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
        model.addAttribute("couponStatusId", couponStatusId);
        model.addAttribute("page",
            couponService.getIssuedCoupon(memberId, couponStatusId, page, size));
        return "coupon/my_coupon_list";
    }
}
