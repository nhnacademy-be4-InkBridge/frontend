package com.nhnacademy.inkbridge.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * class: RootConfig.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
@Configuration
public class RootConfig {
    // TODO: 에러 헨들러 나중에 처리하기

    /**
     * front, api 서버 연결 커넥션 객체 설정 메서드.
     * @return simpleClientHttpRequestFactory 반환
     */
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(3000);
        factory.setReadTimeout(100000);
        factory.setBufferRequestBody(false);

        return factory;
    }

    /**
     * RestTemplate 메서드
     * @param clientHttpRequestFactory 레스트 템플릿 설정 빈
     * @return restTemplate
     */
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        return new RestTemplate(clientHttpRequestFactory);
    }
}
