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
    @Size(message="카테고리 명은 10글자 이하로 작성해야합니다.", min=1,max=10)
    private String categoryName;
}
