package com.nhnacademy.inkbridge.front.dto.coupon;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: CouponStatus.
 *
 * @author JBum
 * @version 2024/02/23
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CouponStatus {

    private Integer couponStatusId;

    private String couponStatusName;

    @Builder
    public CouponStatus(Integer couponStatusId, String couponStatusName) {
        this.couponStatusId = couponStatusId;
        this.couponStatusName = couponStatusName;
    }
}
