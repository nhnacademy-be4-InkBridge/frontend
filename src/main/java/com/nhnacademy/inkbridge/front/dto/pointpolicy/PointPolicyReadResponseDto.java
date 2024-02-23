package com.nhnacademy.inkbridge.front.dto.pointpolicy;


import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * class: PointPolicyReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
@AllArgsConstructor
@Getter
public class PointPolicyReadResponseDto {

    private Long pointPolicyId;
    private String policyType;
    private Long accumulatePoint;
    private LocalDate createdAt;

}
