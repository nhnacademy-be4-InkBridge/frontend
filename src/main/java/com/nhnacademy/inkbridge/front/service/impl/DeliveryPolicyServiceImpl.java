package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.DeliveryPolicyAdaptor;
import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyReadResponseDto;
import com.nhnacademy.inkbridge.front.service.DeliveryPolicyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: DeliveryPolicyServiceImpl.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
@Service
@RequiredArgsConstructor
public class DeliveryPolicyServiceImpl implements DeliveryPolicyService {

    private final DeliveryPolicyAdaptor deliveryPolicyAdaptor;

    /**
     * {@inheritDoc}
     *
     * @return DeliveryPolicyReadResponseDto
     */
    @Override
    public DeliveryPolicyReadResponseDto getCurrentPolicy() {
        return deliveryPolicyAdaptor.getCurrentPolicy();
    }

    /**
     * {@inheritDoc}
     *
     * @return List - DeliveryPolicyReadResponseDto
     */
    @Override
    public List<DeliveryPolicyAdminReadResponseDto> getDeliveryPolicies() {
        return deliveryPolicyAdaptor.getDeliveryPolicies();
    }

    /**
     * {@inheritDoc}
     *
     * @param requestDto DeliveryPolicyCreateRequestDto
     */
    @Override
    public void createDeliceryPolicy(DeliveryPolicyCreateRequestDto requestDto) {
        deliveryPolicyAdaptor.createDeliveryPolicy(requestDto);
    }
}
