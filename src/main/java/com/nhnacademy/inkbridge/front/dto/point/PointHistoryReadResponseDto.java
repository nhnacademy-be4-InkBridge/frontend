package com.nhnacademy.inkbridge.front.dto.point;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * class: PointHistoryReadResponseDto.
 *
 * @author jeongbyeonghun
 * @version 3/22/24
 */
@Getter
@NoArgsConstructor
public class PointHistoryReadResponseDto {


    private String reason;

    private Long point;

    private LocalDateTime accruedAt;

    public PointHistoryReadResponseDto(String reason, Long point, LocalDateTime accruedAt) {
        this.reason = reason;
        this.point = point;
        this.accruedAt = accruedAt;
    }
}