package org.idiot.yesslave.notice.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NoticeUpdateRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @Builder.Default
    private String content = "";
}

