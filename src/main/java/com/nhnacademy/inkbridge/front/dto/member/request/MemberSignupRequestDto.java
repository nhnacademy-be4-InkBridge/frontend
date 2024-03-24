package com.nhnacademy.inkbridge.front.dto.member.request;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * class: MemberSignupRequestDto.
 *
 * @author devminseo
 * @version 3/4/24
 */
@Getter
@AllArgsConstructor
public class MemberSignupRequestDto {
    @Email(message = "이메일 형식이 틀렸습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 8~20자 영문 대 소문자, 숫자 , 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String memberName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "생일은 필수 입력 값입니다.")
    private LocalDate birthday;

    @NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    private String phoneNumber;

    /**
     *  평문 패스워드를 다이제스트로 바꿔주는 메서드.
     *
     * @param encodePassword 평문 패스워드
     */
    public void setEncodePassword(String encodePassword) {
        this.password = encodePassword;
    }
}
