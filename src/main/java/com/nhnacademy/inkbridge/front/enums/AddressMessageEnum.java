package com.nhnacademy.inkbridge.front.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * class: AddressMessageEnum.
 *
 * @author jeongbyeonghun
 * @version 3/13/24
 */
@RequiredArgsConstructor
@Getter
public enum AddressMessageEnum {
    ADDRESS_VALID_FAIL("주소 형식이 잘못 되었습니다.");

    private final String message;
}
