package com.nhnacademy.inkbridge.front.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * class: MemberLoginRequestDto.
 *
 * @author devminseo
 * @version 2/29/24
 */
@Getter
@AllArgsConstructor
public class MemberLoginRequestDto {
    private String email;
    private String password;
}
