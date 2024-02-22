package com.nhnacademy.inkbridge.front.config;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * class: WebConfig.
 *
 * @author minm063
 * @version 2024/02/22
 */
@Configuration
public class WebConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofSeconds(3L))
            .setReadTimeout(Duration.ofSeconds(3L)).build();
    }
}
