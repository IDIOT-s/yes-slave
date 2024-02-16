package org.idiot.yesslave.notice.domain;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.idiot.yesslave.global.jpa.AuditInformation;
import org.idiot.yesslave.notice.dto.NoticeSaveRequest;
import org.idiot.yesslave.notice.dto.NoticeUpdateRequest;

import javax.persistence.*;

@Entity
@Getter
public class Notice extends AuditInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    @Comment("제목")
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    @Comment("내용")
    private String content;

    protected Notice() {
    }

    private Notice(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Notice createNotice(NoticeSaveRequest noticeSaveRequest) {
        return new Notice(noticeSaveRequest.getTitle(), noticeSaveRequest.getContent());
    }

    public void update(NoticeUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
