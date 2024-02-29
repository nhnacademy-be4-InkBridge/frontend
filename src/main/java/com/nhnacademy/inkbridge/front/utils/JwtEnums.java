package com.nhnacademy.inkbridge.front.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * class: JwtUtils.
 *
 * @author devminseo
 * @version 2/29/24
 */
@Getter
@AllArgsConstructor
public enum JwtEnums {
    ACCESS_HEADER("Authorization-Access"),
    REFRESH_HEADER("Authorization-Refresh"),
    BEARER_PREFIX("Bearer "),
    HEADER_REFRESH_EXPIRED_TIME("refresh_expired_time"),
    HEADER_ACCESS_EXPIRED_TIME("access_expired_time"),
    ACCESS_COOKIE("access-cookie"),
    REFRESH_COOKIE("refresh-cookie");
    private final String name;
}
