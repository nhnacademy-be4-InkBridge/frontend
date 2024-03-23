package com.nhnacademy.inkbridge.front.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: CategoryBySearch.
 *
 * @author choijaehun
 * @version 2024/03/23
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryBySearch {

    private Long categoryId;

    private String categoryName;
}
