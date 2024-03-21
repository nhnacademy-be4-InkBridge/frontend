package com.nhnacademy.inkbridge.front.dto.pay;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: PayReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/20
 */
@NoArgsConstructor
@Getter
public class PayInfoResponseDto {

    private Long payId;

    private String paymentKey;

    private String method;

    private String status;

    private LocalDateTime requestedAt;

    private LocalDateTime approvedAt;

    private Long totalAmount;

    private Long balanceAmount;

    private Long vat;

    private Boolean isPartialCancelable;

    private String provider;
}
