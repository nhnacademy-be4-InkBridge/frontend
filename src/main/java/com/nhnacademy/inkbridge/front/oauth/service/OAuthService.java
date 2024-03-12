package com.nhnacademy.inkbridge.front.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.inkbridge.front.adaptor.OAuthAdaptor;
import com.nhnacademy.inkbridge.front.exception.OauthException;
import com.nhnacademy.inkbridge.front.oauth.dto.PaycoResponseDto;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

/**
 * class: OAuthService.
 *
 * @author devminseo
 * @version 3/12/24
 */
@RequiredArgsConstructor
public abstract class OAuthService {
    protected final ObjectMapper objectMapper;
    protected final OAuthAdaptor oAuthAdaptor;

    public abstract String userInfoUri();

    public Map<String,Object> getUserInfo(String token,String userInfoUri) {
        Map<String,Object> userInfo;
        try{
            ResponseEntity<String> payco = oAuthAdaptor.getUserInfo(token, userInfoUri);
            userInfo = objectMapper.readValue(payco.getBody(), Map.class);
        } catch (JsonProcessingException e) {
            throw new OauthException();
        }
        return userInfo;
    }

    public abstract PaycoResponseDto parseDto(Map<String, Object> userInfo);

    public boolean isOauthMember(String id) {
        ResponseEntity<Boolean> result = oAuthAdaptor.isOAuthMember(id);

        return Boolean.TRUE.equals(result.getBody());
    }
}
