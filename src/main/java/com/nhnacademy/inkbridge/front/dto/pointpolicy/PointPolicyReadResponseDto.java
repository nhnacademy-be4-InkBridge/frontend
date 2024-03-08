package com.nhnacademy.inkbridge.front.dto.pointpolicy;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: PointPolicyReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PointPolicyReadResponseDto {

    private Long pointPolicyId;
    private String policyType;
    private Long accumulatePoint;

}
