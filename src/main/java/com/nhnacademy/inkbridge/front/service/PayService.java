package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import org.json.simple.JSONObject;
import org.springframework.transaction.annotation.Transactional;

/**
 * class: PayService.
 *
 * @author jangjaehun
 * @version 2024/03/13
 */
public interface PayService {

    /**
     * 결제 승인 요청을 보내고 결제 로직을 수행합니다.
     *
     * @param requestDto 결제 승인 정보
     * @return 요청 응답
     */
    @Transactional
    JSONObject doConfirm(PayConfirmRequestDto requestDto);
}
