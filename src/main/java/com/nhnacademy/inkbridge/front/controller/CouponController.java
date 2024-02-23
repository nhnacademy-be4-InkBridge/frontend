package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.service.CouponService;
import javax.management.Attribute;
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
        @RequestParam(name = "coupon-type-id", defaultValue = "1") Integer couponTypeId,
        @RequestParam(name = "page-number", defaultValue = "0") Integer pageNumber,
        @RequestParam(name = "size", defaultValue = "10") Integer size, Model model) {
        System.out.println(couponService.getAdminCoupons(couponTypeId,pageNumber,size).toString());
        model.addAttribute("page", couponService.getAdminCoupons(couponTypeId,pageNumber, size));
        return "admin/coupons";
    }
}
