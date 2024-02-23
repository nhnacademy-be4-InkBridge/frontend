package com.nhnacademy.inkbridge.front.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * class: PageRequestDto.
 *
 * @author JBum
 * @version 2024/02/23
 */
@Getter
@ToString
public class PageRequestDto<T> {
    private List<T> Content;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private int numberOfElements;

    @Builder
    public PageRequestDto(List<T> content, int totalPages, int totalElements, int size, int number,
        int numberOfElements) {
        Content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.number = number;
        this.numberOfElements = numberOfElements;
    }
}
