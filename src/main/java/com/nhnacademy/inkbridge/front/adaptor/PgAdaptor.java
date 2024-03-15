package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmResponseDto;

/**
 * class: PayAdaptor.
 *
 * @author jangjaehun
 * @version 2024/03/13
 */
public interface PgAdaptor {

    /**
     * 결제 대행사에 결제 승인 요청을 보냅니다.
     *
     * @param requestDto 요청 데이터
     * @return 요청 응답
     */
    PayConfirmResponseDto doPayConfirm(PayConfirmRequestDto requestDto);
}
