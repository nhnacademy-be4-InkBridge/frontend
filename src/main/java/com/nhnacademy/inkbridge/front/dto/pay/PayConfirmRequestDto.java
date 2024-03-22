package com.nhnacademy.inkbridge.front.dto.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * class: PayReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/07
 */
@Getter
@AllArgsConstructor
public class PayConfirmRequestDto {

    private String orderId;
    private String paymentKey;
    private Long amount;
}