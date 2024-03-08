package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyReadResponseDto;
import java.util.List;

/**
 * class: PointPolicyAdapter.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
public interface PointPolicyAdaptor {

    List<PointPolicyAdminReadResponseDto> getCurrentPointPolicies();

    List<PointPolicyAdminReadResponseDto> getPointPolicies();

    void createPointPolicy(PointPolicyCreateRequestDto requestDto);

    List<PointPolicyAdminReadResponseDto> getPointPoliciesByTypeId(Integer pointPolicyTypeId);

    PointPolicyReadResponseDto getCurrentPointPolicyById(Integer pointPolicyTypeId);
}
