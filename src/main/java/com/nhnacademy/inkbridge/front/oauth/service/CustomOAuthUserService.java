package com.nhnacademy.inkbridge.front.oauth.service;

import com.nhnacademy.inkbridge.front.oauth.dto.CustomOAuth2User;
import com.nhnacademy.inkbridge.front.oauth.dto.OAuth2ResponseDto;
import com.nhnacademy.inkbridge.front.oauth.dto.OAuthUserDto;
import com.nhnacademy.inkbridge.front.oauth.factory.OAuthFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * class: CustomOAuthUserService.
 *
 * @author devminseo
 * @version 3/10/24
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuthUserService extends DefaultOAuth2UserService {
    private final OAuthFactory oAuthFactory;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String accessToken = String.valueOf(userRequest.getAccessToken().getTokenValue());
        log.info("OAuth2 service start -> {}, {}", registrationId, accessToken);


        OAuth2ResponseDto oAuth2ResponseDto = null;
        OAuthService oAuthService = null;
        String idNo = null;
        String email = "null";
        if ("payco".equals(registrationId)) {
            log.info("payco service start ->");
            oAuthService = oAuthFactory.findMathchersService(registrationId);
            oAuth2ResponseDto =
                    oAuthService.parseDto(oAuthService.getUserInfo(accessToken, oAuthService.userInfoUri()));
            idNo = oAuth2ResponseDto.getProviderId();
            log.info("dto -> {}, {}", idNo, oAuth2ResponseDto.getProvider());

        } else {
            return null;
        }


        // 이미 존재하는 OAuth 유저인지 체크
        if (oAuthService.isOauthMember(idNo)) {
            email = oAuthService.getEmail(idNo);
            log.info("email -> {}", email);
        }

        // 첫 로그인시 email null 값
        OAuthUserDto userDto = new OAuthUserDto();
        userDto.setEmail(email);
        userDto.setPassword(idNo);
        userDto.setRole("ROLE_SOCIAL");


        return new CustomOAuth2User(userDto);
    }
}
