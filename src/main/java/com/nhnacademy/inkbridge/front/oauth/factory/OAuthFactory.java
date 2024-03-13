package com.nhnacademy.inkbridge.front.oauth.factory;

import com.nhnacademy.inkbridge.front.exception.InvalidOauthServiceException;
import com.nhnacademy.inkbridge.front.oauth.service.OAuthService;
import com.nhnacademy.inkbridge.front.oauth.service.PaycoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * class: OAuthFactory.
 *
 * @author devminseo
 * @version 3/12/24
 */
@Component
@RequiredArgsConstructor
public class OAuthFactory {
    private final PaycoService paycoService;

    public OAuthService findMathchersService(String name) {
        if (name.equalsIgnoreCase("PAYCO")) {
            return paycoService;
        } else {
            throw new InvalidOauthServiceException();
        }
    }
}
