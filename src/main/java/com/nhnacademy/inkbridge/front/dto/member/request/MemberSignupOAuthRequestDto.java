package com.nhnacademy.inkbridge.front.dto.member.request;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * class: MemberSignupOAuthRequestDto.
 *
 * @author devminseo
 * @version 3/13/24
 */
@Getter
@AllArgsConstructor
public class MemberSignupOAuthRequestDto {
    @Email(message = "이메일 형식이 틀렸습니다.")
    private String email;
    private String password;

    @NotNull(message = "이름은 필수 입력 값입니다.")
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String memberName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "생일은 필수 입력 값입니다.")
    private LocalDate birthday;

    @NotNull(message = "핸드폰 번호는 필수 입력 값입니다.")
    @NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    private String phoneNumber;

    public void setPasswordFromRedis(String password) {
        this.password = password;
    }

    public void setEmailWithSocial() {
        this.email = "SOCIAL " + email;
    }
}
