package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import org.json.simple.JSONObject;

/**
 * class: PayService.
 *
 * @author jangjaehun
 * @version 2024/03/13
 */
public interface PayService {

    /**
     * 결제 승인 요청을 호출하는 메소드입니다.
     *
     * @param requestDto 결제 승인 정보
     * @return 요청 응답
     */
    JSONObject doConfirm(PayConfirmRequestDto requestDto);
}
