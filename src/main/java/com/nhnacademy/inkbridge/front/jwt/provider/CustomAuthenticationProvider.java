package com.nhnacademy.inkbridge.front.jwt.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * class: CustomAuthenticationProvider.
 *
 * @author devminseo
 * @version 2/29/24
 */
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(),
                "",
                null
        );
    }
}
