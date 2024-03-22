package com.nhnacademy.inkbridge.front.dto.pay;

/**
 * class: PayConfirmResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/16
 */
public interface PayConfirmResponseDto {

    String getProvider();

    String getPaymentKey();

    String getOrderId();

    String getTotalAmount();

    String getBalanceAmount();

    String getApprovedAt();

    String getRequestedAt();

    Long getVat();

    Boolean getPartialCancelable();

    String getMethod();

    String getStatus();

}
