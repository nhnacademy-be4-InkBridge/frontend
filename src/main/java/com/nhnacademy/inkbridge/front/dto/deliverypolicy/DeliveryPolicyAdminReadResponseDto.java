package com.nhnacademy.inkbridge.front.dto.deliverypolicy;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * class: DeliveryPolicyAdminReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@RequiredArgsConstructor
@Getter
public class DeliveryPolicyAdminReadResponseDto {

    private final Long deliveryPolicyId;
    private final Long deliveryPrice;
    private final LocalDate createdAt;
    private final Long freeDeliveryPrice;
}
