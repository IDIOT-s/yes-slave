package org.idiot.yesslave.worktimer.repository;

import org.idiot.yesslave.worktimer.domain.VerificationCode;
import org.idiot.yesslave.worktimer.domain.WorkTimer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkTimerRepository extends JpaRepository<WorkTimer, Long> {
    Optional<WorkTimer> findByCheckInAndCode(LocalDate checkIn, VerificationCode code);
}
