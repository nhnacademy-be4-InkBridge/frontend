package com.nhnacademy.inkbridge.front.dto.pay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: PayReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/07
 */
@Getter
@NoArgsConstructor
@Setter
public class PayConfirmRequestDto {

    private String orderId;
    private String paymentKey;
    private Long amount;
}
