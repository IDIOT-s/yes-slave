package org.idiot.yesslave.worktimer.repository;

import org.idiot.yesslave.worktimer.domain.VerificationCode;
import org.idiot.yesslave.worktimer.domain.WorkTimer;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkTimerCustomRepository {
    Optional<WorkTimer> findByCheckInAndCode(LocalDate checkIn, VerificationCode code);
}
