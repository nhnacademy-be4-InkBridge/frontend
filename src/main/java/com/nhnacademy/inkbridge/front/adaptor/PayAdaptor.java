package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.pay.PayCreateRequestDto;

/**
 * class: PayAdaptor.
 *
 * @author jangjaehun
 * @version 2024/03/16
 */
public interface PayAdaptor {

    /**
     * 결제 생성을 Backend 서버로 요청합니다.
     *
     * @param payCreateRequestDto 결제 정보
     */
    void doPay(PayCreateRequestDto payCreateRequestDto);
}
