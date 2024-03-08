package com.nhnacademy.inkbridge.front.dto.wrapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * class: WrappingReadResponseDto.
 *
 * @author jangjaehun
 * @version 2024/03/05
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class WrappingReadResponseDto {

    private Long wrappingId;
    private String wrappingName;
    private Long price;
    private Boolean isActive;
}
