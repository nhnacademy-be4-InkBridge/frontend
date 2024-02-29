package com.nhnacademy.inkbridge.front.jwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * class: CustomUserDetailService.
 *
 * @author devminseo
 * @version 2/29/24
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
