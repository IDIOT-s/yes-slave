package org.idiot.yesslave.worktimer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.idiot.yesslave.global.jpa.AuditInformation;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "WORK_TIMER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkTimer extends AuditInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORK_TIMER_ID", nullable = false)
    private Long id;

    @NotNull
    @Comment("체크인")
    @Column(name = "CHECK_IN", nullable = false)
    private LocalDateTime checkIn;

    @Comment("체크아웃")
    @Column(name = "CHECK_OUT")
    private LocalDateTime checkOut;

    @NotNull
    @Embedded
    @Comment("확인코드")
    @AttributeOverrides({
            @AttributeOverride(name = "verificationCode", column = @Column(name = "VERIFICATION_CODE", length = 6))
    })
    private VerificationCode code;

    public static WorkTimer registerOf(LocalDateTime checkIn, VerificationCode code) {
        return new WorkTimer(
                null
                , checkIn
                , null
                , code
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkTimer workTimer = (WorkTimer) o;
        return Objects.equals(id, workTimer.id) && Objects.equals(checkIn, workTimer.checkIn) && Objects.equals(checkOut, workTimer.checkOut) && Objects.equals(code, workTimer.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkIn, checkOut, code);
    }
}