package com.nhnacademy.inkbridge.front.dto.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * class: PayReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/07
 */
@Getter
@AllArgsConstructor
@ToString
public class PayReadResponseDto {

    private String orderId;
    private String orderName;
    private Long price;

}
