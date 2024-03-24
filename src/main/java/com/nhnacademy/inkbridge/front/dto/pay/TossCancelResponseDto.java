package com.nhnacademy.inkbridge.front.dto.pay;

import java.util.Map;

/**
 * class: TossCancelResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/23
 */
public class TossCancelResponseDto implements PayCancelResponseDto{

    private Map<String, Object> attribute;

    public TossCancelResponseDto(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public Long getTotalAmount() {
        return Long.parseLong(attribute.get("totalAmount").toString());
    }

    @Override
    public Long getBalanceAmount() {
        return Long.parseLong(attribute.get("balanceAmount").toString());
    }

    @Override
    public String getStatus() {
        return attribute.get("status").toString();
    }

    @Override
    public String getOrderId() {
        return attribute.get("orderId").toString();
    }

    @Override
    public Boolean getIsPartialCancelable() {
        return Boolean.parseBoolean(attribute.get("isPartialCancelable").toString());
    }
}
