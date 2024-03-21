package com.nhnacademy.inkbridge.front.config;

import com.nhn.dooray.client.DoorayHookSender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * class: DoorayConfig.
 *
 * @author devminseo
 * @version 3/20/24
 */
@Configuration
@ConfigurationProperties(prefix = "dooray.hook")
@RequiredArgsConstructor
public class DoorayConfig {
    private final RestTemplate restTemplate;
    @Setter
    @Getter
    private String sender;

    @Bean
    public DoorayHookSender doorayHookSender() {
        return new DoorayHookSender(restTemplate, getSender());
    }

}
