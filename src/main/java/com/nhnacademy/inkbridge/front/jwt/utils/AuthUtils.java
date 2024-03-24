package com.nhnacademy.inkbridge.front.jwt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.inkbridge.front.dto.member.response.MemberInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * class: AuthUtils.
 *
 * @author devminseo
 * @version 3/21/24
 */
@Component
@RequiredArgsConstructor
public class AuthUtils {
    private final ObjectMapper objectMapper;

    /**
     * 현재 로그인한 회원 정보를 model 에 담아서 넘겨주는 메서드.
     *
     * @param model model
     */
    public void modelInsertMemberInfo(Model model) {
        String credential = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        MemberInfoResponseDto responseDto;
        try{
            responseDto = objectMapper.readValue(credential, MemberInfoResponseDto.class);
        } catch (Exception e) {
            responseDto = null;
        }

        model.addAttribute("member", responseDto);
    }
}
