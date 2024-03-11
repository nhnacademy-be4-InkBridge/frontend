package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponReadResponseDto;

/**
 * class: CouponService.
 *
 * @author JBum
 * @version 2024/02/26
 */
public interface CouponService {

    /**
     * 관리자가 관리할 쿠폰리스트 호출 메소드.
     *
     * @param couponStatusId 쿠폰의 상태
     * @param page           페이지
     * @param size           사이즈
     * @return 페이징 처리된 쿠폰리스트
     */

    PageRequestDto<CouponReadResponseDto> getAdminCoupons(Integer couponStatusId, Integer page,
        Integer size);

    void createCoupon(CouponCreateRequestDto couponCreateRequestDto);

    PageRequestDto<CouponReadResponseDto> getCoupons(Integer page, Integer size);

    void issueCoupon(String memberId,String couponId);
}
