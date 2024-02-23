package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.adaptor.CouponAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponReadResponseDto;
import org.springframework.stereotype.Service;

/**
 * class: CouponService.
 *
 * @author JBum
 * @version 2024/02/22
 */
@Service
public class CouponService {
    private final CouponAdaptor couponAdaptor;

    public CouponService(CouponAdaptor couponAdaptor) {
        this.couponAdaptor = couponAdaptor;
    }
    public PageRequestDto<CouponReadResponseDto> getAdminCoupons(Integer couponTypeId,Integer pageNumber,Integer size){
        return couponAdaptor.getAdminCoupons(couponTypeId,pageNumber,size);
    }
}
