package com.nhnacademy.inkbridge.front.utils;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * class: HeaderUtils.
 *
 * @author devminseo
 * @version 2/29/24
 */
public class HeaderUtils {
    private HeaderUtils() {
    }

    public static HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return headers;
    }
}
