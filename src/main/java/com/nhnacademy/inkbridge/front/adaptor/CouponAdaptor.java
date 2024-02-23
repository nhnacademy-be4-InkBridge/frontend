package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponType;

/**
 * class: CouponAdaptor.
 *
 * @author JBum
 * @version 2024/02/22
 */
public interface CouponAdaptor {
    PageRequestDto<CouponReadResponseDto> getAdminCoupons(Integer pageNumber, Integer size, Integer couponTypeId);

}
