package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.coupon.BirthDayCouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.BookCouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CategoryCouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.CategoryService;
import com.nhnacademy.inkbridge.front.service.CouponService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@Slf4j
public class AdminCouponController {

    private final CouponService couponService;
    private final CategoryService categoryService;

    /**
     * 쿠폰컨트롤러에 필요한 서비스 등록.
     *
     * @param couponService   쿠폰서비스
     * @param categoryService 카테고리서비스
     */
    public AdminCouponController(CouponService couponService, CategoryService categoryService) {
        this.couponService = couponService;
        this.categoryService = categoryService;
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

    @PostMapping("/register")
    public String createCoupon(
        @Valid @ModelAttribute CouponCreateRequestDto couponCreateRequestDto,
        BindingResult bindingResult,
        HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        couponService.createCoupon(couponCreateRequestDto, httpServletResponse);
        return "redirect:/admin/coupons";
    }

    @GetMapping("/category/register")
    public String createCategoryCouponView(Model model) {
        model.addAttribute("categories", categoryService.readCategory());
        return "admin/category_coupon_create";
    }

    @PostMapping("/category/register")
    public String createCategoryCoupon(
        @Valid @ModelAttribute CategoryCouponCreateRequestDto categoryCouponCreateRequestDto,
        BindingResult bindingResult,
        HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        couponService.createCategoryCoupon(categoryCouponCreateRequestDto, httpServletResponse);
        return "redirect:/admin/coupons";
    }

    @GetMapping("/book/register")
    public String createBookCouponView() {
        return "admin/book_coupon_create";
    }

    @PostMapping("/book/register")
    public String createBookCoupon(
        @Valid @ModelAttribute BookCouponCreateRequestDto bookCouponCreateRequestDto,
        BindingResult bindingResult,
        HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getFieldError().getDefaultMessage());
            throw new ValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        couponService.createBookCoupon(bookCouponCreateRequestDto, httpServletResponse);
        return "redirect:/admin/coupons";
    }

    @GetMapping("/birthday/register")
    public String createBirthDayCouponView() {
        return "admin/birthday_coupon_create";
    }

    @PostMapping("/birthday/register")
    public String createBirthdayCoupon(
        @Valid @ModelAttribute BirthDayCouponCreateRequestDto birthDayCouponCreateRequestDto,
        BindingResult bindingResult,
        HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getFieldError().getDefaultMessage());
            throw new ValidationException(bindingResult.getFieldError().getDefaultMessage());
        }
        couponService.createBirthdayCoupon(birthDayCouponCreateRequestDto, httpServletResponse);
        return "redirect:/admin/coupons";
    }

    @GetMapping("/edit-form")
    public String editCouponView() {
        return "admin/coupon_edit";
    }

    @PutMapping("/edit-form")
    public String editCoupon() {
        return "admin/coupon_edit";
    }
}
