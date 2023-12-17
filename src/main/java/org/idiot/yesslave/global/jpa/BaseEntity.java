package org.idiot.yesslave.global.jpa;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Comment("등록일")
    @Column(name = "REGISTER_DATE", nullable = false, updatable = false)
    private LocalDateTime registerDate;

    @LastModifiedDate
    @Comment("수정일")
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;
}
