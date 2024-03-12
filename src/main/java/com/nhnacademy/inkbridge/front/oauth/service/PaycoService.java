package com.nhnacademy.inkbridge.front.oauth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.inkbridge.front.adaptor.OAuthAdaptor;
import com.nhnacademy.inkbridge.front.oauth.dto.PaycoResponseDto;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * class: PaycoService.
 *
 * @author devminseo
 * @version 3/12/24
 */
@Service
@Slf4j
public class PaycoService extends OAuthService{
    public PaycoService(ObjectMapper objectMapper,
                        OAuthAdaptor oAuthAdaptor) {
        super(objectMapper, oAuthAdaptor);
    }

    @Override
    public String userInfoUri() {
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("apis-payco.krp.toastoven.net")
                .path("payco/friends/find_member_v2.json")
                .build().toUriString();
    }

    @Override
    public PaycoResponseDto parseDto(Map<String, Object> userInfo) {
        log.info("data -> {}", userInfo.get("data"));
        Map<String, Object> data = (Map<String, Object>) userInfo.get("data");


        return new PaycoResponseDto(data);

    }
}
