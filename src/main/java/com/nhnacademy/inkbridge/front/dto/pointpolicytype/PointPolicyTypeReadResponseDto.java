package com.nhnacademy.inkbridge.front.dto.pointpolicytype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: PointPolicyTypeReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointPolicyTypeReadResponseDto {

    private Integer pointPolicyTypeId;
    private String policyType;
}