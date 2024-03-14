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

    JSONObject doConfirm(PayConfirmRequestDto requestDto);
}
