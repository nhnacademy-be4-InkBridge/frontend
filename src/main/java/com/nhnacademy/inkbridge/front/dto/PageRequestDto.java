package com.nhnacademy.inkbridge.front.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * class: PageRequestDto.
 *
 * @author JBum
 * @version 2024/02/23
 */
@Getter
@ToString
@NoArgsConstructor
public class PageRequestDto<T> {

    private List<T> content;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private int numberOfElements;

    /**
     * WAS에서 받은 데이터 페이징 처리할때 사용하는 생성자.
     *
     * @param content          페이징할 컨텐츠
     * @param totalPages       총 페이지
     * @param totalElements    총 개수
     * @param size             화면에 출력할 개수
     * @param number           현재 페이지
     * @param numberOfElements 현재페이지의 개수
     */
    @Builder
    public PageRequestDto(List<T> content, int totalPages, int totalElements, int size, int number,
        int numberOfElements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.number = number;
        this.numberOfElements = numberOfElements;
    }
}
