package com.nhnacademy.inkbridge.front.adapter;

import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyReadResponseDto;
import java.util.List;

/**
 * class: PointPolicyAdapter.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
public interface PointPolicyAdapter {

    List<PointPolicyReadResponseDto> getCurrentPointPolicies();

    List<PointPolicyReadResponseDto> getPointPolicies();

    void createPointPolicy(PointPolicyCreateRequestDto requestDto);

    List<PointPolicyReadResponseDto> getPointPoliciesByTypeId(Integer pointPolicyTypeId);
}
