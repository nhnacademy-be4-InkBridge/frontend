package com.nhnacademy.inkbridge.front.oauth.service;

import com.nhnacademy.inkbridge.front.oauth.dto.CustomOAuth2User;
import com.nhnacademy.inkbridge.front.oauth.dto.OAuth2ResponseDto;
import com.nhnacademy.inkbridge.front.oauth.dto.OAuthUserDto;
import com.nhnacademy.inkbridge.front.oauth.dto.PaycoResponseDto;
import com.nhnacademy.inkbridge.front.oauth.factory.OAuthFactory;
import java.util.Map;
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
        String accessToken = String.valueOf(userRequest.getAccessToken());
        log.info("OAuth2 service start -> {}, {}", registrationId, accessToken);


        OAuth2ResponseDto oAuth2ResponseDto = null;
        if ("payco".equals(registrationId)) {
            log.info("payco service start ->");
            OAuthService paycoService = oAuthFactory.findMathchersService(registrationId);
            Map<String, Object> userInfo = paycoService.getUserInfo(accessToken, paycoService.userInfoUri());
            log.info("payco userInfo -> {}", userInfo.get("member"));

//            oAuth2ResponseDto = new PaycoResponseDto(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String userName = oAuth2ResponseDto.getProvider() + " " + oAuth2ResponseDto.getProviderId();

        // 이미 존재하는 OAuth 유저인지 체크
        // 존재하지 않는다면 회원 정보를 저장한다
        // 존재한다면 로그인 로직 이동 (OAuth 에서 가져온 정보 혹시나 바껴있을 수도 있으니 업데이트 진행).

        OAuthUserDto userDto = new OAuthUserDto();
        userDto.setUserName(userName);
        userDto.setName(oAuth2ResponseDto.getName());
        userDto.setRole("ROLE_MEMBER");

        return new CustomOAuth2User(userDto);
    }
}
