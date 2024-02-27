package com.nhnacademy.inkbridge.front.dto.coupon;

import lombok.Getter;

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
    private int couponTypeId;
    private Boolean isBirth;
    private int couponStatusId;

    /**
     * 쿠폰 읽을 때 사용할 Response.
     *
     * @param couponId         쿠폰ID
     * @param couponName       쿠폰이름
     * @param minPrice         최소가격
     * @param discountPrice    할인가격
     * @param maxDiscountPrice 최대할인가격
     * @param basicIssuedDate  쿠폰발급시작일
     * @param basicExpiredDate 쿠폰발급만료일
     * @param validity         쿠폰유효기간
     * @param couponTypeId     쿠폰타입(%,원)
     * @param isBirth          생일여부
     * @param couponStatusId   쿠폰상태
     */
    public CouponReadResponseDto(String couponId, String couponName, Long minPrice,
        Long discountPrice,
        Long maxDiscountPrice, String basicIssuedDate, String basicExpiredDate,
        Integer validity,
        int couponTypeId, Boolean isBirth, int couponStatusId) {
        this.couponId = couponId;
        this.couponName = couponName;
        this.minPrice = minPrice;
        this.discountPrice = discountPrice;
        this.maxDiscountPrice = maxDiscountPrice;
        this.basicIssuedDate = basicIssuedDate;
        this.basicExpiredDate = basicExpiredDate;
        this.validity = validity;
        this.couponTypeId = couponTypeId;
        this.isBirth = isBirth;
        this.couponStatusId = couponStatusId;
    }
}
