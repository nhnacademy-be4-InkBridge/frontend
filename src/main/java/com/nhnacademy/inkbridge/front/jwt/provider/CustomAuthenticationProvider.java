package com.nhnacademy.inkbridge.front.jwt.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

/**
 * class: CustomAuthenticationProvider.
 *
 * @author devminseo
 * @version 2/29/24
 */
@Slf4j
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User userDetails =
                (User) this.getUserDetailsService().loadUserByUsername((String) authentication.getPrincipal());

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
    }
}
