package com.nhnacademy.inkbridge.front.dto.wrapping;

import lombok.AccessLevel;
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
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class WrappingCreateRequestDto {

    private String wrappingName;
    private Long price;
    private Boolean isActive;

//    @Builder
//    public WrappingCreateRequestDto(String wrappingName, Long price, Boolean isActive) {
//        this.wrappingName = wrappingName;
//        this.price = price;
//        this.isActive = isActive;
//    }
}
