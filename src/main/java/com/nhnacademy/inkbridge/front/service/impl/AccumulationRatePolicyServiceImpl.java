package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.AccumulationRatePolicyAdaptor;
import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.accumulationratepolicy.AccumulationRatePolicyReadResponseDto;
import com.nhnacademy.inkbridge.front.service.AccumulationRatePolicyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: AccumulationRatePolicyServiceImpl.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@Service
@RequiredArgsConstructor
public class AccumulationRatePolicyServiceImpl implements AccumulationRatePolicyService {

    private final AccumulationRatePolicyAdaptor accumulationRatePolicyAdaptor;

    /**
     * {@inheritDoc}
     *
     * @return AccumulationRatePolicyReadResponseDto
     */
    @Override
    public AccumulationRatePolicyReadResponseDto getCurrentPolicy() {
        return accumulationRatePolicyAdaptor.getCurrentPolicy();
    }

    /**
     * {@inheritDoc}
     *
     * @return List - AccumulationRatePolicyAdminReadResponseDto
     */
    @Override
    public List<AccumulationRatePolicyAdminReadResponseDto> getAccumulationRatePolicies() {
        return accumulationRatePolicyAdaptor.getAccumulationRatePolicies();
    }

    /**
     * {@inheritDoc}
     *
     * @param requestDto AccumulationRatePolicyCreateRequestDto
     */
    @Override
    public void createAccumulationRatePolicy(AccumulationRatePolicyCreateRequestDto requestDto) {
        accumulationRatePolicyAdaptor.createAccumulationRatePolicy(requestDto);
    }
}
