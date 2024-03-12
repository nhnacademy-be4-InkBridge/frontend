package com.nhnacademy.inkbridge.front.adaptor.impl;

import static com.nhnacademy.inkbridge.front.utils.CommonUtils.createHeader;

import com.nhnacademy.inkbridge.front.adaptor.OAuthAdaptor;
import com.nhnacademy.inkbridge.front.property.GatewayProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * class: OAuthAdaptorImpl.
 *
 * @author devminseo
 * @version 3/12/24
 */
@Component
@RequiredArgsConstructor
public class OAuthAdaptorImpl implements OAuthAdaptor {
    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;
    @Override
    public ResponseEntity<String> getUserInfo(String token, String url) {
        HttpHeaders headers = createHeader();
        headers.add("client_id","3RDIcz8jl50Q7AdOBmUDrBJ");
        headers.add("access_token", token);


        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(headers),
                String.class
        );
    }

    @Override
    public ResponseEntity<Boolean> isOAuthMember(String id) {
        return restTemplate.exchange(
                gatewayProperties.getUrl() + "/api/oauth" + id,
                HttpMethod.GET,
                new HttpEntity<>(createHeader()),
                Boolean.class
        );
    }
}
