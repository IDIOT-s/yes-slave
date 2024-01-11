package org.idiot.yesslave.notice.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class NoticeSaveRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    private String content = "";

    private NoticeSaveRequest() {
    }

    public NoticeSaveRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
