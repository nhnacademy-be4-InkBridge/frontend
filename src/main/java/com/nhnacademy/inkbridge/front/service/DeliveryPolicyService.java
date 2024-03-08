package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyAdminReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.deliverypolicy.DeliveryPolicyAdminReadResponseDto;
import java.util.List;

/**
 * class: DeliveryPolicyService.
 *
 * @author jangjaehun
 * @version 2024/02/25
 */
public interface DeliveryPolicyService {

    /**
     * 현재 적용중인 배송비 정책을 조회하는 메소드입니다.
     *
     * @return DeliveryPolicyReadResponseDto
     */
    DeliveryPolicyAdminReadResponseDto getCurrentPolicy();

    /**
     * 전체 배송비 정책 변경 내역을 조회하는 메소드입니다.
     *
     * @return List - DeliveryPolicyReadResponseDto
     */
    List<DeliveryPolicyAdminReadResponseDto> getDeliveryPolicies();

    /**
     * 배송비 정책을 생성하는 메소드입니다.
     *
     * @param requestDto DeliveryPolicyCreateRequestDto
     */
    void createDeliceryPolicy(DeliveryPolicyCreateRequestDto requestDto);
}
