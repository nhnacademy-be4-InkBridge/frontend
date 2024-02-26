package com.nhnacademy.inkbridge.front.dto.coupon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.ToString;

/**
 * class: CouponListResponseDto.
 *
 * @author JBum
 * @version 2024/02/22
 */
@Getter
public class CouponReadResponseDto {

    private String couponId;
    private String couponName;
    private Long minPrice;
    private Long discountPrice;
    private Long maxDiscountPrice;
    private String basicIssuedDate;
    private String basicExpiredDate;
    private Integer validity;
    private CouponType couponType;
    private Boolean isBirth;
    private CouponStatus couponStatus;

    public CouponReadResponseDto(String couponId, String couponName, Long minPrice,
        Long discountPrice,
        Long maxDiscountPrice, String basicIssuedDate, String basicExpiredDate,
        Integer validity,
        CouponType couponType, Boolean isBirth, CouponStatus couponStatus) {
        this.couponId = couponId;
        this.couponName = couponName;
        this.minPrice = minPrice;
        this.discountPrice = discountPrice;
        this.maxDiscountPrice = maxDiscountPrice;
        this.basicIssuedDate = basicIssuedDate;
        this.basicExpiredDate = basicExpiredDate;
        this.validity = validity;
        this.couponType = couponType;
        this.isBirth = isBirth;
        this.couponStatus = couponStatus;
    }
}
