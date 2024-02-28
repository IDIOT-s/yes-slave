package org.idiot.yesslave.notice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.idiot.yesslave.notice.domain.Notice;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeFindResponse {
    private Long id;
    private String title;
    private String content;

    @Builder
    public NoticeFindResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static NoticeFindResponse of(Notice notice) {
        return NoticeFindResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .build();
    }
}
