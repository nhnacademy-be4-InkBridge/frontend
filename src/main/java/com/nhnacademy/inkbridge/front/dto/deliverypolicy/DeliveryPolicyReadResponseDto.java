package com.nhnacademy.inkbridge.front.dto.deliverypolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * class: DeliveryPolicyOrderReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/01
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DeliveryPolicyReadResponseDto {

    private Long deliveryPolicyId;
    private Long deliveryPrice;
    private Long freeDeliveryPrice;

}
