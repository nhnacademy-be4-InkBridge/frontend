package com.nhnacademy.inkbridge.front.dto.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: PayConfirmResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/15
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PayConfirmResponseDto {
    private String paymentKey;
    private String orderId;
    private String totalAmount;
    private String balanceAmount;
    private String approvedAt;
    private String requestedAt;
    private Integer vat;
    private Boolean isPartialCancelable;
}
