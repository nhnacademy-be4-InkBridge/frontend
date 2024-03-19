package com.nhnacademy.inkbridge.front.dto.coupon;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * class: CouponOrderReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/09
 */
@Getter
@NoArgsConstructor
@ToString
public class MemberCouponReadResponseDto {

    private Long memberCouponId;
    private LocalDate expiredAt;
    private LocalDate usedAt;
    private String couponName;
    private Long minPrice;
    private Long discountPrice;
    private Long maxDiscountPrice;
    private Integer couponTypeId;
    private String couponTypeName;
    private Boolean isBirth;
    private Integer couponStatusId;
    private String couponStatusName;
}