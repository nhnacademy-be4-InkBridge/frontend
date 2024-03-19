package com.nhnacademy.inkbridge.front.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: MemberPointReadResponseDto.
 *
 * @author jeongbyeonghun
 * @version 3/19/24
 */

@NoArgsConstructor
@Getter
public class MemberPointReadResponseDto {

    Long point;

    @Builder
    public MemberPointReadResponseDto(Long point) {
        this.point = point;
    }
}
