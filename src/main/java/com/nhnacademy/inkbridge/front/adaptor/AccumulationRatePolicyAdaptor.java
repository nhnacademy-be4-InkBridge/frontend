package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyReadResponseDto;
import java.util.List;

/**
 * class: AccumulationRatePolicyAdaptor.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
public interface AccumulationRatePolicyAdaptor {

    /**
     * 현재 적용되는 적립률 정책을 조회하는 메소드입니다.
     *
     * @return AccumulationRatePolicyReadResponseDto
     */
    AccumulationRatePolicyReadResponseDto getCurrentPolicy();

    /**
     * 전체 적립률 정책 변경 내역을 조회하는 메소드입니다.
     *
     * @return List - AccumulationRatePolicyReadResponseDto
     */
    List<AccumulationRatePolicyAdminReadResponseDto> getAccumulationRatePolicies();

    /**
     * 적용할 적립률 정책을 생성하는 메소드입니다.
     *
     * @param requestDto AccumulationRatePolicyCreateRequestDto
     */
    void createAccumulationRatePolicy(AccumulationRatePolicyCreateRequestDto requestDto);
}
