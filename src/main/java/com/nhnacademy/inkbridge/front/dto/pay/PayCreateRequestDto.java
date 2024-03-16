package com.nhnacademy.inkbridge.front.dto.pay;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * class: PayCreateRequestDto.
 *
 * @author jangjaehun
 * @version 2024/03/15
 */
@AllArgsConstructor
@Getter
@ToString
public class PayCreateRequestDto {

    private String payKey;
    private String orderCode;
    private String totalAmount;
    private String balanceAmount;
    private LocalDateTime approvedAt;
    private LocalDateTime requestedAt;
    private Long vat;
    private Boolean isPartialCancelable;
    private String provider;
    private String method;
    private String status;

    public PayCreateRequestDto(PayConfirmResponseDto responseDto) {
        payKey = responseDto.getPaymentKey();
        orderCode = responseDto.getOrderId();
        totalAmount = responseDto.getTotalAmount();
        balanceAmount = responseDto.getBalanceAmount();
        approvedAt = LocalDateTime.parse(responseDto.getApprovedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
        requestedAt = LocalDateTime.parse(responseDto.getRequestedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
        vat = responseDto.getVat();
        isPartialCancelable = responseDto.getPartialCancelable();
        provider = responseDto.getProvider();
        method = responseDto.getMethod();
        status = responseDto.getStatus();
    }
}
