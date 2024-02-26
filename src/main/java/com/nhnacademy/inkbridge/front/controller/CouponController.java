package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.service.CouponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping
    public String view(
        @RequestParam(name = "coupon-status-id", defaultValue = "1") Integer couponStatusId,
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size, Model model) {
        model.addAttribute("couponStatusId", couponStatusId);
        model.addAttribute("page", couponService.getAdminCoupons(couponStatusId,page, size));
        return "admin/coupon/coupon_list";
    }
}
