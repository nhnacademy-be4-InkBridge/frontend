package com.nhnacademy.inkbridge.front.adaptor;

import org.springframework.http.ResponseEntity;

/**
 * class: OAuthAdaptor.
 *
 * @author devminseo
 * @version 3/12/24
 */
public interface OAuthAdaptor {
    /**
     * oauth 소셜 로그인 서비스에서 회원 정보 가져오는 메서드.
     *
     * @param token 소셜 서비스에서 받은 토큰
     * @param url oauth 회원 정보 가져오는 경로
     * @return 회원 정보
     */
    ResponseEntity<String> getUserInfo(String token, String url);

    /**
     * oauth로 로그인 한적 있는 회원인지 체크하는 메서드.
     *
     * @param id 회원 아이디
     * @return 체크 유무
     */
    ResponseEntity<Boolean> isOAuthMember(String id);
}
