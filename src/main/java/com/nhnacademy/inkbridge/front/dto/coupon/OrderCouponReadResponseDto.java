package com.nhnacademy.inkbridge.front.dto.coupon;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * class: OrderCouponReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/09
 */
@Getter
@NoArgsConstructor
@ToString
public class OrderCouponReadResponseDto {

    private Long bookId;
    private List<MemberCouponReadResponseDto> memberCouponReadResponseDtos;
}
