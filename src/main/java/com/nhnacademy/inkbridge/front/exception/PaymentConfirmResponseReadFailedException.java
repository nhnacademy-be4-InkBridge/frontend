package com.nhnacademy.inkbridge.front.exception;

/**
 * class: PaymentResponseReadFailedException.
 *
 * @author jangjaehun
 * @version 2024/03/17
 */
public class PaymentConfirmResponseReadFailedException extends RuntimeException {

    public PaymentConfirmResponseReadFailedException() {
        super("결제 승인 정보를 읽는데 실패했습니다.");
    }
}
