package com.nhnacademy.inkbridge.front.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: AuthorBySearch.
 *
 * @author choijaehunz
 * @version 2024/03/17
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorBySearch {

    private Long authorId;
    private String authorName;
}
