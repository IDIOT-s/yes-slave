package org.idiot.yesslave.notice.dto;

import lombok.Builder;
import lombok.Getter;
import org.idiot.yesslave.notice.domain.Notice;

@Getter
@Builder
public class NoticeFindResponse {
    private Long id;
    private String title;
    private String content = "";

    private NoticeFindResponse() {
    }

    public static NoticeFindResponse of(Notice notice) {
        return NoticeFindResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .build();
    }
}
