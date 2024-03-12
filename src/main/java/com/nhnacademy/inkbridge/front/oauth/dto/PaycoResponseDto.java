package com.nhnacademy.inkbridge.front.oauth.dto;

import java.util.Map;

/**
 * class: PaycoResponseDto.
 *
 * @author devminseo
 * @version 3/11/24
 */
public class PaycoResponseDto implements OAuth2ResponseDto {
    private final Map<String,Object> attribute;

    public PaycoResponseDto(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "payco";
    }

    @Override
    public String getProviderId() {
        return attribute.get("idNo").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getMobile() {
        return attribute.get("mobile").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }

    @Override
    public String getBirthday() {
        return attribute.get("birthdayMMdd").toString();
    }

}
