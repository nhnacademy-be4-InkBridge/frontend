package com.nhnacademy.inkbridge.front.oauth.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * class: CustomOAuth2User.
 *
 * @author devminseo
 * @version 3/11/24
 */
public class CustomOAuth2User implements OAuth2User {
    private final OAuthUserDto oAuthUserDto;

    public CustomOAuth2User(OAuthUserDto oAuthUserDto) {
        this.oAuthUserDto = oAuthUserDto;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) oAuthUserDto::getRole);
        return collection;
    }

    @Override
    public String getName() {
        return oAuthUserDto.getName();
    }

    public String getUserName() {
        return oAuthUserDto.getUserName();
    }
}
