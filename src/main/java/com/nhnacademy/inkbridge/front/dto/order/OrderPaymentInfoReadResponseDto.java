package com.nhnacademy.inkbridge.front.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: OrderPaymentInfoReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/12
 */
@Getter
@NoArgsConstructor
public class OrderPaymentInfoReadResponseDto {

    private String orderCode;
    private String orderName;
    private Long amount;
}
