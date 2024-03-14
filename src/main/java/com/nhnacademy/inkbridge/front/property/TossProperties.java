package com.nhnacademy.inkbridge.front.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * class: TossProperties.
 *
 * @author jangjaehun
 * @version 2024/03/12
 */
@ConfigurationProperties(prefix = "toss")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class TossProperties {

    private final String clientKey;

    private final String apiKey;

}
