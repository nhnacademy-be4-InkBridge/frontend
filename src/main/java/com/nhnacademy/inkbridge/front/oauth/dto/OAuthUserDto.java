package com.nhnacademy.inkbridge.front.oauth.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * class: OAuthUserDto.
 *
 * @author devminseo
 * @version 3/11/24
 */
@Getter
@Setter
public class OAuthUserDto {
    private String userName;
    private String name;
    private String role;
}
