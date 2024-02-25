package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyReadResponseDto;
import java.util.List;

/**
 * class: DeliveryPolicyAdaptor.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
public interface DeliveryPolicyAdaptor {

    /**
     * 현재 적용 배송비 정책을 조회하는 메소드입니다.
     *
     * @return DeliveryPolicyReadResponseDto
     */
    DeliveryPolicyReadResponseDto getCurrentPolicy();

    /**
     * 배송비 변경 내역을 조회하는 메소드입니다.
     *
     * @return List - DeliveryPolicyReadResponseDto
     */
    List<DeliveryPolicyReadResponseDto> getDeliveryPolicies();

    /**
     * 적용할 배송비 정책을 생성하는 메소드입니다.
     *
     * @param requestDto DeliveryPolicyCreateRequestDto
     */
    void createDeliveryPolicy(DeliveryPolicyCreateRequestDto requestDto);
}
