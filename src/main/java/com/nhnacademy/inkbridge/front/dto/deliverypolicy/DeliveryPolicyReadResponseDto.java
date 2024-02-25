package com.nhnacademy.inkbridge.front.dto.deliverypolicy;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * class: DeliveryPolicyReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@RequiredArgsConstructor
@Getter
public class DeliveryPolicyReadResponseDto {

    private final Long deliveryPolicyId;
    private final Long deliveryPrice;
    private final LocalDate createdAt;
}
