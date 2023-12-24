package org.idiot.yesslave.worktimer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "타이머 생성 요청 DTO")
class CheckInRequest {
    @NotNull
    @Schema(description = "생성 일자")
    private LocalDateTime checkIn;
}
