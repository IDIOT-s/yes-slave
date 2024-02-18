package org.idiot.yesslave.notice.dto;

import lombok.*;
import org.idiot.yesslave.notice.domain.Notice;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NoticeFindResponse {
    private Long id;
    private String title;
    private String content;

    public static NoticeFindResponse of(Notice notice) {
        return NoticeFindResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .build();
    }
}