package com.nhnacademy.inkbridge.front.dto.deliverypolicy;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * class: DeliveryPolicyOrderReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/01
 */
@RequiredArgsConstructor
@Getter
public class DeliveryPolicyReadResponseDto {

    private final Long deliveryPolicyId;
    private final Long deliveryPrice;
    private final LocalDate createdAt;
    private final Long freeDeliveryPrice;

}
