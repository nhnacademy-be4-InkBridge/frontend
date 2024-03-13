package com.nhnacademy.inkbridge.front.dto.wrapping;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * class: WrappingReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class WrappingCreateRequestDto {

    @Length(max = 20, message = "20글자가 최대입니다")
    @NotNull(message = "포장지 이름을 입력해주세요.")
    @NotBlank(message = "포장지 이름이 공란입니다.")
    private String wrappingName;

    @NotNull(message = "가격을 입력해주세요.")
    @Min(value = 0, message = "최소 0원이상의 가격으로 작성해주세요")
    private Long price;

    @NotNull(message = "값이 비어있습니다")
    private Boolean isActive;
}
