package com.nhnacademy.inkbridge.front.dto.member.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: MemberPasswordRequestDto.
 *
 * @author devminseo
 * @version 3/22/24
 */
@Getter
@NoArgsConstructor
public class MemberPasswordRequestDto {
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 8~20자 영문 대 소문자, 숫자 , 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 8~20자 영문 대 소문자, 숫자 , 특수문자를 사용하세요.")
    private String newPassword;

    /**
     *  평문 패스워드를 다이제스트로 바꿔주는 메서드.
     *
     * @param encodePassword 평문 패스워드
     */
    public void setEncodeNewPassword(String encodePassword) {
        this.newPassword = encodePassword;
    }
}
