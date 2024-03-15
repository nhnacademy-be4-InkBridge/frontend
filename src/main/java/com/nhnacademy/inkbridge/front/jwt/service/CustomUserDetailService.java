package com.nhnacademy.inkbridge.front.jwt.service;

import static com.nhnacademy.inkbridge.front.jwt.utils.JwtEnums.MEMBER_INFO;

import com.nhnacademy.inkbridge.front.adaptor.CartAdaptor;
import com.nhnacademy.inkbridge.front.adaptor.MemberAdaptor;
import com.nhnacademy.inkbridge.front.dto.cart.CartReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.member.response.MemberInfoResponseDto;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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
    private final CartAdaptor cartAdaptor;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String accessToken) throws UsernameNotFoundException {
        String uuid = accessToken.split("\\.")[3];
        String token = accessToken.substring(0, accessToken.length() - (uuid.length() + 1));

        MemberInfoResponseDto dto = memberAdaptor.getMemberInfoByToken(token);

        if (Objects.isNull(dto)) {
            log.error("회원을 찾을 수 없습니다.");
            throw new UsernameNotFoundException("회원을 찾을 수 없습니다.");
        }

        Long memberId = dto.getMemberId();
        List<CartReadResponseDto> cartList = cartAdaptor.getCart(
            memberId);
        redisTemplate.opsForHash().putAll(String.valueOf(memberId), cartList.stream().collect(
            Collectors.toMap(key -> String.valueOf(key.getBookId()),
                value -> String.valueOf(value.getAmount()))));

        redisTemplate.opsForHash().put(uuid, MEMBER_INFO.getName(), dto);

        List<SimpleGrantedAuthority> authorities = dto.getRoles().stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        return new User(
            dto.getMemberId().toString(),
            "",
            authorities);
    }

}
