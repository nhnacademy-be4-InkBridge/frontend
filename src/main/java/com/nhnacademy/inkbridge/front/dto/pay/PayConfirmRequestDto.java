package com.nhnacademy.inkbridge.front.dto.pay;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: PayReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/07
 */
@Getter
@NoArgsConstructor
public class PayConfirmRequestDto {

    private String orderId;
    private String paymentKey;
    private Long amount;

}
