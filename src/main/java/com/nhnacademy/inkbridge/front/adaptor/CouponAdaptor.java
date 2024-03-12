package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponReadResponseDto;

/**
 * class: CouponAdaptor.
 *
 * @author JBum
 * @version 2024/02/22
 */
public interface CouponAdaptor {

    /**
     * 관리자용 쿠폰리스트 WAS에게 요청하는 메소드.
     *
     * @param couponStatusId 쿠폰상태
     * @param page           페이지
     * @param size           사이즈
     * @return 페이징처리된 쿠폰리스트
     */
    PageRequestDto<CouponReadResponseDto> getAdminCoupons(Integer couponStatusId,
        Integer page, Integer size);

    void setCoupons(CouponCreateRequestDto couponCreateRequestDto);

    PageRequestDto<CouponReadResponseDto> getCoupons(Integer page, Integer size);

    void issueCoupon(String memberId, String couponId);

    PageRequestDto<CouponReadResponseDto> getIssuedCoupon(String memberId, Integer couponStatusId,
        Integer page, Integer size);
}
