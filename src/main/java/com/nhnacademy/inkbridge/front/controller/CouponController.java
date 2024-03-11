package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.service.CouponService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class: CouponController.
 *
 * @author JBum
 * @version 2024/03/11
 */
@Controller
@RequestMapping("/coupons")
public class CouponController {
    private CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping
    public String issueCouponsView(@RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "size", defaultValue = "30") Integer size, Model model) {
        model.addAttribute("page", couponService.getCoupons(page,size));

        return "coupon/issue_coupon_list";
    }

    @PostMapping("/issue")
    public String issueCoupon(@RequestParam("coupon_id")String couponId){
        String memberId = (String) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
        couponService.issueCoupon(memberId, couponId);
        return "redirect:/coupons";
    }
}
