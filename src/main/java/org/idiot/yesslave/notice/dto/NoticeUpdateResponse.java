package org.idiot.yesslave.notice.dto;

import lombok.*;
import org.idiot.yesslave.notice.domain.Notice;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NoticeUpdateResponse {
    private Long id;
    private String title;
    private String content;

    public static NoticeUpdateResponse of(Notice notice) {
        return NoticeUpdateResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .build();
    }
}
