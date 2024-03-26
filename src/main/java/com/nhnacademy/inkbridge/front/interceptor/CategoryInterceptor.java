package com.nhnacademy.inkbridge.front.interceptor;

import com.nhnacademy.inkbridge.front.dto.category.ParentCategoryReadResponseDto;
import com.nhnacademy.inkbridge.front.service.BookService;
import com.nhnacademy.inkbridge.front.service.CategoryService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * class: CategoryInterceptor.
 *
 * @author choijaehun
 * @version 2024/03/23
 */

@Component
@RequiredArgsConstructor
public class CategoryInterceptor implements HandlerInterceptor {

    private final CategoryService categoryService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        if(modelAndView !=null){
            List<ParentCategoryReadResponseDto> parentCategories = categoryService.readCategory();
            modelAndView.addObject("parentCategories", parentCategories);

        }
    }
}
