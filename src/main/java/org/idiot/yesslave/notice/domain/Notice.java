package org.idiot.yesslave.notice.domain;

import lombok.Getter;
import org.idiot.yesslave.global.jpa.AuditInformation;

import javax.persistence.*;

@Entity
@Getter
public class Notice extends AuditInformation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(length=50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

/*
    @ManyToOne(fetch=FetchType.LAZY)
    @Column(name="USER_ID")
    private Long userId;
*/
    protected Notice() {}
    private Notice(String title, String content){
        this.title=title;
        this.content=content;
    }


    public static Notice createNotice(String title, String content){
        return new Notice(title, content);
    }
}
