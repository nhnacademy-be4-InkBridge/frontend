package com.nhnacademy.inkbridge.front.dto.pay;

/**
 * class: PayCancelResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/23
 */
public interface PayCancelResponseDto {
    Long getTotalAmount();
    Long getBalanceAmount();
    String getStatus();
    String getOrderId();
    Boolean getIsPartialCancelable();
}
