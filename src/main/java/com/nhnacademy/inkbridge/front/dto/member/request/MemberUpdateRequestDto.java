package com.nhnacademy.inkbridge.front.dto.member.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * class: MemberUpdateRequestDto.
 *
 * @author devminseo
 * @version 3/21/24
 */
@Getter
@AllArgsConstructor
public class MemberUpdateRequestDto {
    @NotBlank(message = "이름은 필수 입력 값 입니다.")
    private String memberName;
    @Email(message = "이메일이 형식에 맞지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값 입니다.")
    private String email;
    @NotBlank(message = "핸드폰은 필수 입력 값 입니다.")
    private String phoneNumber;
}
