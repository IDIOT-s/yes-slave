package org.idiot.yesslave.global.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "입력된 값에 대한 유효성 검사에 실패했을 경우의 응답값")
public class InvalidResponse {
    @Schema(description = "오류가 발생한 field명", example = "verificationCode")
    private String field;

    @Schema(description = "오류 메세지", example = "확인번호를 입력해주세요.")
    private String message;

    @Schema(description = "잘못 입력된 field 입력 값", example = "ABCDE")
    private Object rejectValue;
}
