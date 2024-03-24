package com.nhnacademy.inkbridge.front.dto.pay;

import lombok.Getter;

/**
 * class: PayCancelRequestDto.
 *
 * @author jangjaehun
 * @version 2024/03/23
 */
@Getter
public class PayCancelRequestDto {

    private String orderCode;
    private String status;
    private Long totalAmount;
    private Long balanceAmount;
    private Boolean isPartialCancelable;

    public PayCancelRequestDto(PayCancelResponseDto responseDto) {
        this.orderCode = responseDto.getOrderId();
        this.status = responseDto.getStatus();
        this.totalAmount = responseDto.getTotalAmount();
        this.balanceAmount = responseDto.getBalanceAmount();
        this.isPartialCancelable = responseDto.getIsPartialCancelable();
    }
}
