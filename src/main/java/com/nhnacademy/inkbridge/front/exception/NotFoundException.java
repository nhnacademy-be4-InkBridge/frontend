package com.nhnacademy.inkbridge.front.exception;

/**
 * class: NotFoundException.
 *
 * @author jangjaehun
 * @version 2024/03/15
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
