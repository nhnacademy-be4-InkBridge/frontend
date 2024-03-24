package com.nhnacademy.inkbridge.front.exception;

import com.nhnacademy.inkbridge.front.service.MemberService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/**
 * class: ExceptionAdvice.
 *
 * @author devminseo
 * @version 3/6/24
 */
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {
    private final MemberService memberService;

    @ExceptionHandler({MemberNotFoundException.class})
    public String goLogin() {
        return "redirect:/";
    }

    @ExceptionHandler({UnAuthorizedException.class})
    public String unAuthorization(HttpServletResponse response) {
        memberService.logout(response);
        return "redirect:/";
    }

    @ExceptionHandler({HttpClientErrorException.NotFound.class, HttpServerErrorException.class})
    public String goError() {
        return "redirect:/404";
    }

    @ExceptionHandler(Exception.class)
    public String goCommonError() {
        return "redirect:/404";
    }
}
