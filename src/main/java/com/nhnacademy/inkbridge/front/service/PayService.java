package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.pay.PayCancelInfoDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayCancelResponseDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmResponseDto;

/**
 * class: PayService.
 *
 * @author jangjaehun
 * @version 2024/03/13
 */
public interface PayService {

    /**
     * 결제를 진행하는 메소드입니다.
     *
     * @param requestDto 결제 승인 정보
     * @param provider 결제 회사
     */
    void doPayment(PayConfirmRequestDto requestDto, String provider);

    /**
     * 취소를 진행하는 메소드입니다.
     *
     * @param paymentKey 결제 키
     * @param requestDto 취소 요청 정보
     * @param provider 결제 회사
     */
    void doCancel(String paymentKey, PayCancelInfoDto requestDto, String provider);

    /**
     * 결제 승인 요청을 보냅니다.
     *
     * @param requestDto 결제 승인 정보
     * @param provider 결제 회사
     * @return 요청 응답
     */
    PayConfirmResponseDto doConfirm(PayConfirmRequestDto requestDto, String provider);

    /**
     * 결제 취소 요청을 보냅니다.
     * @param paymentKey 결제 키
     * @param requestDto 취소 정보
     * @param provider 결제 회사
     * @return 취소 응답
     */
    PayCancelResponseDto doCancelPayment(String paymentKey, PayCancelInfoDto requestDto, String provider);

    /**
     * 결제 취소를 진행합니다.
     *
     * @param requestDto 취소 정보
     */
    void cancelPay(PayCancelResponseDto requestDto);

    /**
     * 결제 정보를 저장하는 메소드입니다.
     *
     * @param requestDto 결제 정보
     */
    void registerPay(PayConfirmResponseDto requestDto);
}
