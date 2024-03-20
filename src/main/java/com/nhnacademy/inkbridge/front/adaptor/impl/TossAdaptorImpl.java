package com.nhnacademy.inkbridge.front.adaptor.impl;

import com.nhnacademy.inkbridge.front.adaptor.PaymentCompanyAdaptor;
import com.nhnacademy.inkbridge.front.config.KeyConfig;
import com.nhnacademy.inkbridge.front.dto.pay.PayConfirmRequestDto;
import com.nhnacademy.inkbridge.front.property.TossProperties;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * class: TossAdaptorImpl.
 *
 * @author jangjaehun
 * @version 2024/03/13
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TossAdaptorImpl implements PaymentCompanyAdaptor {

    private final RestTemplate restTemplate;
    private final TossProperties tossProperties;
    private final KeyConfig keyConfig;

    /**
     * {@inheritDoc}
     *
     * @param requestDto 요청 데이터
     * @return 요청 응답
     */
    @Override
    public String doPayConfirm(PayConfirmRequestDto requestDto) {
        String apiKey = keyConfig.keyStore(tossProperties.getApiKey());

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((apiKey + ":").getBytes(StandardCharsets.UTF_8));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBasicAuth(new String(encodedBytes));

        HttpEntity<PayConfirmRequestDto> entity = new HttpEntity<>(requestDto, httpHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange(
            "https://api.tosspayments.com/v1/payments/confirm",
            HttpMethod.POST,
            entity,
            String.class);

        return exchange.getBody();
    }
}
