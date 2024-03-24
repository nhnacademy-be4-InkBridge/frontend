package com.nhnacademy.inkbridge.front.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * class: PayMessageEnum.
 *
 * @author jangjaehun
 * @version 2024/03/15
 */
@RequiredArgsConstructor
@Getter
public enum PayMessageEnum {
    PAYMENT_GATEWAY_NOT_FOUND_EXCEPTION("해당하는 결제 대행사를 찾지 못했습니다.");

    private final String message;
}
