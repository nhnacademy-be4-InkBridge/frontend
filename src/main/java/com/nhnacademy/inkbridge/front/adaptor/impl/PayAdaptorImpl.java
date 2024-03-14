package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.PayAdaptor;
import com.nhnacademy.inkbridge.front.config.KeyConfig;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import com.nhnacademy.inkbridge.front.property.TossProperties;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: PayAdaptorImpl.
 *
 * @author jangjaehun
 * @version 2024/03/13
 */
@Component
@RequiredArgsConstructor
public class PayAdaptorImpl implements PayAdaptor {

    private final RestTemplate restTemplate;
    private final TossProperties tossProperties;
    private final KeyConfig keyConfig;

    @Override
    public JSONObject doPayConfirm(PayConfirmRequestDto requestDto) {
        String apiKey = keyConfig.keyStore(tossProperties.getApiKey());

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((apiKey + ":").getBytes(StandardCharsets.UTF_8));

        String authorizations = "Basic " + new String(encodedBytes, 0, encodedBytes.length);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", authorizations);

        HttpEntity<PayConfirmRequestDto> entity = new HttpEntity<>(requestDto, httpHeaders);

        return restTemplate.exchange(
            "https://api.tosspayments.com/v1/payments/confirm",
            HttpMethod.POST,
            entity,
            JSONObject.class).getBody();
    }
}
