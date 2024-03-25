package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.CouponAdaptor;
import com.nhnacademy.inkbridge.front.dto.PageRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.BirthDayCouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.BookCouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CategoryCouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.coupon.CouponReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.coupon.MemberCouponReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.coupon.OrderCouponReadResponseDto;
import com.nhnacademy.inkbridge.front.exception.NotFoundException;
import com.nhnacademy.inkbridge.front.service.CouponService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
    @Override
    public PageRequestDto<CouponReadResponseDto> getAdminCoupons(Integer couponStatusId,
        Integer page, Integer size) {
        return couponAdaptor.getAdminCoupons(couponStatusId, page, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCoupon(CouponCreateRequestDto couponCreateRequestDto,
        HttpServletResponse httpServletResponse) {
        try {
            couponAdaptor.setCoupons(couponCreateRequestDto, httpServletResponse);
        } catch (IOException e) {
            throw new NotFoundException("접근차단");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<CouponReadResponseDto> getCoupons(Integer page, Integer size) {
        return couponAdaptor.getCoupons(page, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void issueCoupon(String memberId, String couponId,
        HttpServletResponse httpServletResponse) {
        try {
            couponAdaptor.issueCoupon(memberId, couponId, httpServletResponse);
        } catch (IOException e) {
            throw new NotFoundException("접근차단");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageRequestDto<MemberCouponReadResponseDto> getIssuedCoupon(String memberId,
        String status,
        Integer page, Integer size) {
        return couponAdaptor.getIssuedCoupon(memberId, status, page, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderCouponReadResponseDto> getOrderCoupons(Long
        memberId, List<String> bookIds) {
        return couponAdaptor.getOrderCoupons(memberId, bookIds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCategoryCoupon(
        CategoryCouponCreateRequestDto categoryCouponCreateRequestDto,
        HttpServletResponse httpServletResponse) {
        try {
            couponAdaptor.setCategoryCoupon(categoryCouponCreateRequestDto, httpServletResponse);
        } catch (IOException e) {
            throw new NotFoundException("접근차단");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createBookCoupon(BookCouponCreateRequestDto bookCouponCreateRequestDto,
        HttpServletResponse httpServletResponse) {
        try {
            couponAdaptor.setBookCoupon(bookCouponCreateRequestDto, httpServletResponse);
        } catch (IOException e) {
            throw new NotFoundException("접근차단");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createBirthdayCoupon(
        BirthDayCouponCreateRequestDto birthDayCouponCreateRequestDto,
        HttpServletResponse httpServletResponse) {
        try {
            couponAdaptor.setBirthdayCoupon(birthDayCouponCreateRequestDto, httpServletResponse);
        } catch (IOException e) {
            throw new NotFoundException("접근차단");
        }
    }

}
