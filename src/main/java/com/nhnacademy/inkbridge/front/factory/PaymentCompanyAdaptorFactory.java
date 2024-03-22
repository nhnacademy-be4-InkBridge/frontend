package com.nhnacademy.inkbridge.front.factory;

import com.nhnacademy.inkbridge.front.adaptor.PaymentCompanyAdaptor;
import com.nhnacademy.inkbridge.front.adaptor.impl.TossAdaptorImpl;
import com.nhnacademy.inkbridge.front.enums.PayMessageEnum;
import com.nhnacademy.inkbridge.front.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * class: PaymentGatewayFactory.
 *
 * @author jangjaehun
 * @version 2024/03/15
 */
@Component
@RequiredArgsConstructor
public class PaymentCompanyAdaptorFactory {

    private final TossAdaptorImpl payAdaptor;

    public PaymentCompanyAdaptor findMatchesAdaptor(String vendor) {
        if ("toss".equals(vendor)) {
            return payAdaptor;
        } else {
            throw new NotFoundException(PayMessageEnum.PAYMENT_GATEWAY_NOT_FOUND_EXCEPTION.getMessage());
        }
    }
}
