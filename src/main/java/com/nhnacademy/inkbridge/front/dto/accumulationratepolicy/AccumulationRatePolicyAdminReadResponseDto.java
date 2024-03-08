package com.nhnacademy.inkbridge.front.dto.accumulationratepolicy;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: AccumulationRatePolicyAdminReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccumulationRatePolicyAdminReadResponseDto {

    private Long accumulationRatePolicyId;
    private Integer accumulationRate;
    private LocalDate createdAt;
}
