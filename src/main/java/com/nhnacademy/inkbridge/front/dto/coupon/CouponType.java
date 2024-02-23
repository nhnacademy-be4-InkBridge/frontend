package com.nhnacademy.inkbridge.front.dto.coupon;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: CouponType.
 *
 * @author nhn
 * @version 2024/02/08
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CouponType {

    private Integer couponTypeId;

    private String typeName;

    @Builder
    public CouponType(Integer couponTypeId,String typeName) {
        this.couponTypeId = couponTypeId;
        this.typeName=typeName;
    }
}
