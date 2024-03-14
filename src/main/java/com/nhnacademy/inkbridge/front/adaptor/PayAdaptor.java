package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import org.json.simple.JSONObject;

/**
 * class: PayAdaptor.
 *
 * @author jangjaehun
 * @version 2024/03/13
 */
public interface PayAdaptor {

    JSONObject doPayConfirm(PayConfirmRequestDto requestDto);
}
