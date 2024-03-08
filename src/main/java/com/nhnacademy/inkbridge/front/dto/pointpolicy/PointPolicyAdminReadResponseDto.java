package com.nhnacademy.inkbridge.front.dto.pointpolicy;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: PointPolicyAdminReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PointPolicyAdminReadResponseDto {

    private Long pointPolicyId;
    private String policyType;
    private Long accumulatePoint;
    private LocalDate createdAt;

}