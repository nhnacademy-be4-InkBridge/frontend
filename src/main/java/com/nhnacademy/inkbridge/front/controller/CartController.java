package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.book.BookRedisReadResponseDto;
import com.nhnacademy.inkbridge.front.dto.cart.CartRedisCreateRequestDto;
import com.nhnacademy.inkbridge.front.service.CartService;
import com.nhnacademy.inkbridge.front.utils.CommonUtils;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    private static final int A_WEEK = 60 * 60 * 24 * 7;
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * 장바구니를 조회하는 메서드입니다.
     *
     * @param model   Model
     * @param request HttpServletRequest
     * @return html
     */
    @GetMapping
    public String getCart(Model model, HttpServletRequest request) {
        String memberId = String.valueOf(CommonUtils.getMemberId());
        if (Objects.equals(memberId, "null")) {
            memberId = checkCookie(request.getCookies());
        }

        Map<String, Long> cartInfo = cartService.getCartRedis(memberId);
        Map<String, BookRedisReadResponseDto> bookInfo = cartService.getBookInfo(
            new ArrayList<>(cartInfo.keySet()));
        model.addAttribute("cartInfo", cartInfo);
        model.addAttribute("bookInfo", bookInfo);
        return "member/cart";
    }

    /**
     * 장바구니를 저장하는 메서드입니다.
     *
     * @param cartRedisCreateRequestDto CartRedisCreateRequestDto
     * @param request                   HttpServletRequest
     * @param response                  HttpServletResponse
     * @return html
     */
    @PostMapping
    public String saveCart(@RequestBody CartRedisCreateRequestDto cartRedisCreateRequestDto,
        HttpServletRequest request, HttpServletResponse response) {
        String memberId = String.valueOf(CommonUtils.getMemberId());
        if (Objects.equals(memberId, "null")) {
            memberId = checkCookie(request.getCookies());
            Cookie cookie = new Cookie("cart", memberId);
            cookie.setMaxAge(A_WEEK);
            cookie.setPath("/");
            response.addCookie(cookie);
            cartService.createCart(cartRedisCreateRequestDto, memberId);
        } else {
            cartService.createCartForMember(cartRedisCreateRequestDto, memberId);
        }

        return "redirect:/cart";
    }

    /**
     * 장바구니에서 도서를 삭제하는 메서드입니다.
     *
     * @param bookId  String
     * @param request HttpServletRequest
     * @return html
     */
    @PostMapping("/delete/{bookId}")
    public String deleteCartBook(@PathVariable String bookId, HttpServletRequest request) {
        String memberId = String.valueOf(CommonUtils.getMemberId());
        if (Objects.equals(memberId, "null")) {
            memberId = checkCookie(request.getCookies());
        }

        cartService.deleteCartBook(bookId, memberId);
        return "redirect:/cart";
    }

    /**
     * 장바구니의 도서 수량 증감에 따라 수량을 수정하는 메서드입니다.
     *
     * @param bookId String
     * @param amount String
     * @return HttpStatus
     */
    @PostMapping("/book/{bookId}")
    @ResponseBody
    public ResponseEntity<HttpStatus> updateCartBook(@PathVariable String bookId,
        @RequestParam String amount) {
        cartService.updateCartBook(bookId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
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
