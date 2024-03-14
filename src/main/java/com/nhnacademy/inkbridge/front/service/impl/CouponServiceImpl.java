package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.CouponAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.coupon.OrderCouponReadResponseDto;
import com.nhnacademy.inkbridge.front.service.CouponService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * class: CouponService.
 *
 * @author JBum
 * @version 2024/02/22
 */
@Service
public class CouponServiceImpl implements CouponService {

    private final CouponAdaptor couponAdaptor;


    /**
     * 쿠폰서비스에게 필요한 어댑터 호출.
     *
     * @param couponAdaptor 쿠폰어댑터
     */
    public CouponServiceImpl(CouponAdaptor couponAdaptor) {
        this.couponAdaptor = couponAdaptor;
    }

    /**
     * {@inheritDoc}
     */
    public PageRequestDto<CouponReadResponseDto> getAdminCoupons(Integer couponStatusId,
        Integer page, Integer size) {
        return couponAdaptor.getAdminCoupons(couponStatusId, page, size);
    }

    @Override
    public void createCoupon(CouponCreateRequestDto couponCreateRequestDto) {
        System.out.println(couponCreateRequestDto.toString());
        couponAdaptor.setCoupons(couponCreateRequestDto);
    }

    @Override
    public List<OrderCouponReadResponseDto> getOrderCoupons(Long memberId, List<String> bookIds) {
        return couponAdaptor.getOrderCoupons(memberId, bookIds);
    }
}
