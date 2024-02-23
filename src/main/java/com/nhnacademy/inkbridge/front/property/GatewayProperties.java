package com.nhnacademy.inkbridge.front.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * class: AccountProperties.
 *
 * @author jangjaehun
 * @version 2024/02/22
 */
@ConfigurationProperties(prefix = "gateway.server")
@Configuration
@Getter
@Setter
public class GatewayProperties {

    private String url;

}
