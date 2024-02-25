package com.nhnacademy.inkbridge.front.dto.accumulationratepolicy;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * class: AccumulationRatePolicyReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@Getter
@RequiredArgsConstructor
public class AccumulationRatePolicyReadResponseDto {
    private final Long accumulationRatePolicyId;
    private final Integer accumulationRate;
    private final LocalDate createdAt;
}
