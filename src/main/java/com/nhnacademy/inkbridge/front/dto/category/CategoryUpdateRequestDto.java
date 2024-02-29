package com.nhnacademy.inkbridge.front.dto.category;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class: CategoryUpdateRequestDto.
 *
 * @author choijaehun
 * @version 2024/02/25
 */

@NoArgsConstructor
@Getter
@Setter
public class CategoryUpdateRequestDto {

    private String categoryName;
}
