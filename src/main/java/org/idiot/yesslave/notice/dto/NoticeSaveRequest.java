package org.idiot.yesslave.notice.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class NoticeSaveRequest {
    @NotBlank
    private String title;
    private String content;

    public NoticeSaveRequest() {
    }

    public NoticeSaveRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
