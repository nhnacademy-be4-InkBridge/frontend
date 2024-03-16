package com.nhnacademy.inkbridge.front.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.inkbridge.front.adaptor.PayAdaptor;
import com.nhnacademy.inkbridge.front.adaptor.PaymentCompanyAdaptor;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmResponseDto;
import com.nhnacademy.inkbridge.front.dto.pay.PayCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.pay.TossConfirmResponseDto;
import com.nhnacademy.inkbridge.front.exception.PaymentConfirmResponseReadFailedException;
import com.nhnacademy.inkbridge.front.factory.PaymentCompanyAdaptorFactory;
import com.nhnacademy.inkbridge.front.service.PayService;
import java.util.Map;
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


    private final PaymentCompanyAdaptorFactory paymentCompanyAdaptorFactory;
    private final PayAdaptor payAdaptor;
    private final ObjectMapper objectMapper;

    /**
     * {@inheritDoc}
     *
     * @param requestDto 결제 승인 정보
     * @return 요청 응답
     */
    @Override
    public PayConfirmResponseDto doConfirm(PayConfirmRequestDto requestDto, String provider) {
        PaymentCompanyAdaptor payCompanyAdaptor = paymentCompanyAdaptorFactory.findMatchesAdaptor(provider);
        String payConfirmJsonResponse = payCompanyAdaptor.doPayConfirm(requestDto);


        PayConfirmResponseDto responseDto = null;
        try {
            Map<String, Object> attribute = objectMapper.readValue(payConfirmJsonResponse, Map.class);

            if ("toss".equals(provider)) {
                responseDto = new TossConfirmResponseDto(attribute);
            }

            log.debug("complete toss responseDto {}", attribute);

        } catch (JsonProcessingException exception) {
            throw new PaymentConfirmResponseReadFailedException();
        }

        return responseDto;
    }

    @Override
    public void doPayment(PayConfirmResponseDto responseDto) {

        PayCreateRequestDto payCreateRequestDto = new PayCreateRequestDto(responseDto);
        log.debug("payCraeteRequestDto {}", payCreateRequestDto);
        payAdaptor.doPay(payCreateRequestDto);
    }
}
