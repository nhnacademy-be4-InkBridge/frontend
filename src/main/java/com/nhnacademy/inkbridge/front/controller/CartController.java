package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.cart.CartBookReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartReadResponseDto;
import com.nhnacademy.inkbridge.front.service.CartService;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class: CartController.
 *
 * @author minm063
 * @version 2024/03/03
 */
@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String getCart(Model model, HttpServletRequest request) {
        String memberId = String.valueOf(CommonUtils.getMemberId());
        if (Objects.equals(memberId, "null")) {
            memberId = checkCookie(request.getCookies());
        }
        CartReadResponseDto cart = cartService.getCart(memberId);

        model.addAttribute("bookIds", cart.getBookId());
        model.addAttribute("info", cart.getBookInfo());
        model.addAttribute("totalPrice",
            cart.getBookInfo().stream().mapToLong(CartBookReadResponseDto::getPrice).sum());
        log.info("bookIds: {}", cart.getBookId().keySet());
        return "member/cart";
    }

    @PostMapping
    public String saveCart(@ModelAttribute CartCreateRequestDto cartCreateRequestDto,
        HttpServletRequest request, HttpServletResponse response) {
        String memberId = String.valueOf(CommonUtils.getMemberId());
        if (Objects.equals(memberId, "null")) {
            memberId = checkCookie(request.getCookies());
            Cookie cookie = new Cookie("cart", memberId);
            cookie.setMaxAge(60 * 60 * 24 * 7); // 일주일
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        cartService.createCart(cartCreateRequestDto, memberId);
        return "redirect:/cart";
    }

    private String checkCookie(Cookie[] cookies) {
        String memberId = String.valueOf(UUID.randomUUID());
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("cart".equals(cookie.getName())) {
                    memberId = cookie.getValue();
                }
            }
        }
        return memberId;
    }

}
