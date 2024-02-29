package com.nhnacademy.inkbridge.front.dto.category;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: CategoryCreateRequestDto.
 *
 * @author choijaehun
 * @version 2024/02/24
 */
@NoArgsConstructor
@Getter
@Setter
public class CategoryCreateRequestDto {

    private String categoryName;
    private Long parentId;
}
