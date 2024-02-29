package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.PointPolicyAdaptor;
import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pointpolicy.PointPolicyReadResponseDto;
import com.nhnacademy.inkbridge.front.service.PointPolicyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: PointPolicyServiceImpl.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
@Service
@RequiredArgsConstructor
public class PointPolicyServiceImpl implements PointPolicyService {

    private final PointPolicyAdaptor pointPolicyAdapter;

    /**
     * 현재 적용중인 포인트 정책 리스트를 호출하는 메소드입니다.
     *
     * @return List - PointPolicyReadResponseDto
     */
    @Override
    public List<PointPolicyReadResponseDto> getCurrentPointPolicies() {
        return pointPolicyAdapter.getCurrentPointPolicies();
    }

    /**
     * 전체 포인트 정책 변경 내역을 호출하는 메소드입니다.
     *
     * @return List - PointPolicyReadResponseDto
     */
    @Override
    public List<PointPolicyReadResponseDto> getPointPolicies() {
        return pointPolicyAdapter.getPointPolicies();
    }

    /**
     * 포인트 정책을 생성하는 메소드입니다.
     *
     * @param requestDto PointPolicyCreateRequestDto
     */
    @Override
    public void createPointPolicy(PointPolicyCreateRequestDto requestDto) {
        pointPolicyAdapter.createPointPolicy(requestDto);
    }

    /**
     * 포인트 유형의 전체 변경 내역을 호출하는 메소드입니다.
     *
     * @param pointPolicyTypeId Integer
     * @return List - PointPolicyReadResponseDto
     */
    @Override
    public List<PointPolicyReadResponseDto> getPointPoliciesByTypeId(Integer pointPolicyTypeId) {
        return pointPolicyAdapter.getPointPoliciesByTypeId(pointPolicyTypeId);
    }
}
