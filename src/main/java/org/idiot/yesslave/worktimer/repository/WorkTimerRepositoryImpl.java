package org.idiot.yesslave.worktimer.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.worktimer.domain.QWorkTimer;
import org.idiot.yesslave.worktimer.domain.VerificationCode;
import org.idiot.yesslave.worktimer.domain.WorkTimer;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class WorkTimerRepositoryImpl implements WorkTimerCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<WorkTimer> findByCheckInAndCode(LocalDate checkIn, VerificationCode code) {
        QWorkTimer workTimer = QWorkTimer.workTimer;

        return Optional.ofNullable(jpaQueryFactory.selectFrom(workTimer)
                .where(workTimer.checkIn.after(checkIn.atStartOfDay())
                        .and(workTimer.checkIn.before(checkIn.plusDays(1).atStartOfDay()))
                        .and(workTimer.code.eq(code)))
                .limit(1)
                .fetchOne());
    }
}
