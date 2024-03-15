package com.nhnacademy.inkbridge.front.dto.pay;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * class: PayCreateRequestDto.
 *
 * @author jangjaehun
 * @version 2024/03/15
 */
@AllArgsConstructor
@Getter
public class PayCreateRequestDto {
    private String payKey;
    private String orderCode;
    private String totalAmount;
    private String balanceAmount;
    private LocalDateTime approvedAt;
    private LocalDateTime requestedAt;
    private Long vat;
    private Boolean isPartialCancelable;
    private String vendor;
}
