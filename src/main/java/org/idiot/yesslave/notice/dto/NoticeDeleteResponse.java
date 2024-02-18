package org.idiot.yesslave.notice.dto;

import lombok.*;
import org.idiot.yesslave.notice.domain.Notice;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NoticeDeleteResponse {
    private Long id;
    private String title;

    public static NoticeDeleteResponse of(Notice notice) {
        return NoticeDeleteResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .build();
    }
}
