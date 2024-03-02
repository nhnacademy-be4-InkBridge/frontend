package com.nhnacademy.inkbridge.front.member.adaptor;

import com.nhnacademy.inkbridge.front.member.dto.request.MemberLoginRequestDto;
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
     * @param memberLoginRequestDto 로그인 요청 정보
     * @return 토큰 정보를 헤더로 가져옴
     */
    ResponseEntity<Void> login(MemberLoginRequestDto memberLoginRequestDto);
}
