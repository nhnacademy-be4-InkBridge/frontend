package com.nhnacademy.inkbridge.front.service;

import com.nhnacademy.inkbridge.front.dto.member.MemberPointReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberEmailRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberPasswordRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupOAuthRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberSignupRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.request.MemberUpdateRequestDto;
import com.nhnacademy.inkbridge.front.dto.member.response.MemberInfoResponseDto;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

/**
 * class: MemberService.
 *
 * @author devminseo
 * @version 3/4/24
 */
public interface MemberService {
    /**
     * 멤버 회원가입 메서드
     *
     * @param memberSignupRequestDto 회원가입 폼 정보
     */
    void signup(MemberSignupRequestDto memberSignupRequestDto);

    /**
     * 소셜 멤버 회원가입 메서드
     *
     * @param memberSignupOAuthRequestDto 소셜 멤버 가입 정보
     */
    void signupWithOAuth(MemberSignupOAuthRequestDto memberSignupOAuthRequestDto);

    /**
     * 로그아웃하는 메서드
     *
     * @param response response
     */
    void logout(HttpServletResponse response);

    /**
     * 로그인 시키는 메서드
     *
     * @param email email
     * @param password password
     * @return 결과값
     */
    ResponseEntity<Void> doLogin(String email, String password);


    MemberPointReadResponseDto getPoint();
  

    /**
     * 이메일 중복체크하는 메서드
     *
     * @param memberEmailRequestDto 이메일
     * @return 중복여부
     */
    ResponseEntity<Boolean> isDuplicatedEmail(MemberEmailRequestDto memberEmailRequestDto);

    /**
     * 회원 정보를 가져옵는 메서드.
     *
     * @return 회원 정보
     */
    MemberInfoResponseDto getMemberInfo();

    /**
     * 회원 정보를 업데이트하는 메서드.
     *
     * @param memberUpdateRequestDto 업데이트할 회원 정보
     */
    void updateMember(MemberUpdateRequestDto memberUpdateRequestDto,Long memberId);

    /**
     * 회원의 비밀번호를 변경하는 메서드.
     *
     * @param memberPasswordRequestDto 기존 비밀번호와 새로운 비밀번호
     * @return 성공 여부
     */
    Boolean updatePassword(MemberPasswordRequestDto memberPasswordRequestDto);

    /**
     * 회원 탈퇴하는 메서드.
     *
     * @param memberId 회원 아이디
     */
    void deleteMember(Long memberId);
}
