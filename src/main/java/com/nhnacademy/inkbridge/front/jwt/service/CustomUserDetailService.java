package com.nhnacademy.inkbridge.front.jwt.service;

import com.nhnacademy.inkbridge.front.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.dto.member.response.MemberInfoResponseDto;
import com.nhnacademy.inkbridge.front.member.excpetion.MemberNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    private final MemberAdaptor memberAdaptor;
    @Override
    public UserDetails loadUserByUsername(String accessToken) throws UsernameNotFoundException {
        log.info("adaptor start ->");
        MemberInfoResponseDto dto = memberAdaptor.getMemberInfoByToken(accessToken);


        if (Objects.isNull(dto)) {
            throw new MemberNotFoundException();
        }

        List<SimpleGrantedAuthority> authorities = dto.getRoles().stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        log.info("adaptor end -> {}", authorities);
        return new User(
                dto.getMemberId().toString(),
                "",
                authorities);
    }

}
