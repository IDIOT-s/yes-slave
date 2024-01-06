package org.idiot.yesslave.worktimer.repository;

import org.idiot.yesslave.worktimer.domain.VerificationCode;
import org.idiot.yesslave.worktimer.domain.WorkTimer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface WorkTimerRepository extends JpaRepository<WorkTimer, Long> {

    Optional<WorkTimer> findByCheckInGreaterThanEqualAndCode(@Param("checkIn") LocalDateTime checkIn, @Param("code") VerificationCode code);
}
