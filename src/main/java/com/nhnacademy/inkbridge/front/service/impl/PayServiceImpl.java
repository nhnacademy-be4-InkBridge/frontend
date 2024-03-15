package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.PgAdaptor;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmResponseDto;
import com.nhnacademy.inkbridge.front.factory.PaymentGatewayAdaptorFactory;
import com.nhnacademy.inkbridge.front.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * class: PayServiceImpl.
 *
 * @author jangjaehun
 * @version 2024/03/13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PayServiceImpl implements PayService {


    private final PaymentGatewayAdaptorFactory paymentGatewayAdaptorFactory;

    /**
     * {@inheritDoc}
     *
     * @param requestDto 결제 승인 정보
     * @return 요청 응답
     */
    @Override
    public PayConfirmResponseDto doConfirm(PayConfirmRequestDto requestDto, String vendor) {
        PgAdaptor payAdaptor = paymentGatewayAdaptorFactory.findMatchesAdaptor(vendor);
        return payAdaptor.doPayConfirm(requestDto);
    }
}
