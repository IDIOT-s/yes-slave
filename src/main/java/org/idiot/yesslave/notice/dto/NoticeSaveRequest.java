package org.idiot.yesslave.notice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeSaveRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    private String content;

    @Builder
    public NoticeSaveRequest(String title, String content) {
        if (content == null) {
            content = "";
        }
        this.title = title;
        this.content = content;
    }
}
