package com.nhnacademy.inkbridge.front.dto.member.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: MemberInfoResponseDto.
 *
 * @author devminseo
 * @version 3/2/24
 */
@Getter
@NoArgsConstructor
public class MemberInfoResponseDto {
    private Long memberId;
    private String memberName;
    private String email;
    private String phoneNumber;
    private String birthday;
    private Long memberPoint;
    private List<String> roles;
}
