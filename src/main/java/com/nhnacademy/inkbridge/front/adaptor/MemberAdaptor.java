package com.nhnacademy.inkbridge.front.adaptor;

import com.nhnacademy.inkbridge.front.dto.member.MemberPointReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberLoginRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupOAuthRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.response.MemberInfoResponseDto;
import org.springframework.http.ResponseEntity;

/**
 * class: MemberAdaptor.
 *
 * @author devminseo
 * @version 2/29/24
 */
public interface MemberAdaptor {

    /**
     * 로그인 하기 위한 auth 서버와 통신
     *
     * @param memberLoginRequestDto 로그인 요청 정보
     * @return 토큰 정보를 헤더로 가져옴
     */
    ResponseEntity<Void> login(MemberLoginRequestDto memberLoginRequestDto);

    /**
     * 토큰 인증을 통해 멤버의 정보를 가져옴
     *
     * @param accessToken 인가 토큰
     * @return 인증 받은 유저 정보
     */
    MemberInfoResponseDto getMemberInfoByToken(String accessToken);

    /**
     * 사이트 매번 접속시마다 토큰이 만료되었을대 재발급 요청
     *
     * @param access access 토큰
     * @param refresh refresh 토큰
     * @return access 토큰 헤더로 다시 받아옴
     */
    ResponseEntity<Void> reissueToken(String access, String refresh);

    /**
     * 회원가입 요청
     *
     * @param memberSignupRequestDto 회원가입 폼
     */
    void signup(MemberSignupRequestDto memberSignupRequestDto);

    void signupWithOAuth(MemberSignupOAuthRequestDto memberSignupOAuthRequestDto);

    void logout(String access, String refresh);

    ResponseEntity<Void> doLogin(String id, String password);

    MemberPointReadResponseDto getPoint();
}
