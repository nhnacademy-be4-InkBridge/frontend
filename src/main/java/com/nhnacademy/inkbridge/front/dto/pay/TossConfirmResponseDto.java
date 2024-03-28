package com.nhnacademy.inkbridge.front.dto.pay;

import java.util.Map;
import lombok.Getter;

/**
 * class: PayConfirmResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/15
 */
@Getter
public class TossConfirmResponseDto implements PayConfirmResponseDto {

    private Map<String, Object> attribute;

    public TossConfirmResponseDto(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "toss";
    }

    @Override
    public String getPaymentKey() {
        return attribute.get("paymentKey").toString();
    }

    @Override
    public String getOrderId() {
        return attribute.get("orderId").toString();
    }

    @Override
    public String getTotalAmount() {
        return attribute.get("totalAmount").toString();
    }

    @Override
    public String getBalanceAmount() {
        return attribute.get("totalAmount").toString();
    }

    @Override
    public String getApprovedAt() {
        return attribute.get("approvedAt").toString();
    }

    @Override
    public String getRequestedAt() {
        return attribute.get("requestedAt").toString();
    }

    @Override
    public Long getVat() {
        return Long.valueOf(attribute.get("vat").toString());
    }

    @Override
    public Boolean getPartialCancelable() {
        return (Boolean) attribute.get("isPartialCancelable");
    }

    @Override
    public String getMethod() {
        return attribute.get("method").toString();
    }

    @Override
    public String getStatus() {
        return attribute.get("status").toString();
    }
}
