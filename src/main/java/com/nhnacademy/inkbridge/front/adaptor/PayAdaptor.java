package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.pay.PayCreateRequestDto;

/**
 * class: PayAdaptor.
 *
 * @author jangjaehun
 * @version 2024/03/16
 */
public interface PayAdaptor {

    void doPay(PayCreateRequestDto payCreateRequestDto);
}
