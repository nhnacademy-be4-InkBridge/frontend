package com.nhnacademy.inkbridge.front.dto.deliverypolicy;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * class: DeliveryPolicyReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DeliveryPolicyReadResponseDto {

    private Long deliveryPolicyId;
    private Long deliveryPrice;
    private LocalDate createdAt;
    private Long freeDeliveryPrice;
}
