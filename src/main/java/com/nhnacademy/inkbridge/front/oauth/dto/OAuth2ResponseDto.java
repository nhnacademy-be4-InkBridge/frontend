package com.nhnacademy.inkbridge.front.oauth.dto;

/**
 * class: OAuth2ResponseDto.
 *
 * @author devminseo
 * @version 3/11/24
 */
public interface OAuth2ResponseDto {
    String getProvider();

    String getProviderId();

    String getEmail();

    String getMobile();
    String getName();

    String getBirthday();
}
