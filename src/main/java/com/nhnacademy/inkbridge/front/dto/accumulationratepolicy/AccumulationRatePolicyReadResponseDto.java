package com.nhnacademy.inkbridge.front.dto.accumulationratepolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: AccumulationRatePolicyReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccumulationRatePolicyReadResponseDto {

    private Long accumulationRatePolicyId;
    private Integer accumulationRate;
}
