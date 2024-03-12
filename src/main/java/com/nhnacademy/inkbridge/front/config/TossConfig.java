package com.nhnacademy.inkbridge.front.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * class: TossConfig.
 *
 * @author jangjaehun
 * @version 2024/03/12
 */
@ConfigurationProperties(prefix = "toss")
public class TossConfig {
    @Getter
    private String clientKey;
    private String apiKey;
    private KeyConfig keyConfig;

    public String getApiKey() {
        return keyConfig.keyStore(apiKey);
    }
}
