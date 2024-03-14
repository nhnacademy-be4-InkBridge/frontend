package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.PayAdaptor;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import com.nhnacademy.inkbridge.front.service.PayService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

/**
 * class: PayServiceImpl.
 *
 * @author jangjaehun
 * @version 2024/03/13
 */
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final PayAdaptor payAdaptor;

    @Override
    public JSONObject doConfirm(PayConfirmRequestDto requestDto) {
        return payAdaptor.doPayConfirm(requestDto);
    }
}
