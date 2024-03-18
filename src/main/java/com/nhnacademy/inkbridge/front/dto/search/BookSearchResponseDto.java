package com.nhnacademy.inkbridge.front.dto.search;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: BookSearchReadResponseDto.
 *
 * @author choijaehun
 * @version 2024/03/11
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchResponseDto {

    private Long id;
    private String bookTitle;
    private LocalDateTime publicatedAt;
    private Long regularPrice;
    private Long price;
    private Double discountRatio;

    @Override
    public String toString() {
        return "BookSearchResponseDto{" +
            "id=" + id +
            ", bookTitle='" + bookTitle + '\'' +
            ", publicatedAt=" + publicatedAt +
            ", regularPrice=" + regularPrice +
            ", price=" + price +
            ", discountRatio=" + discountRatio +
            ", view=" + view +
            ", score=" + score +
            ", reviewQuantity=" + reviewQuantity +
            ", publisherId=" + publisherId +
            ", publisherName='" + publisherName + '\'' +
            ", statusName='" + statusName + '\'' +
            ", fileName='" + fileUrl + '\'' +
            ", authors=" + authors +
            ", tags=" + tags +
            '}';
    }

    private Long view;
    private Double score;
    private Long reviewQuantity;
    private Long publisherId;
    private String publisherName;
    private String statusName;
    private String fileUrl;
    private List<AuthorBySearch> authors;
    private List<TagBySearch> tags;
}
