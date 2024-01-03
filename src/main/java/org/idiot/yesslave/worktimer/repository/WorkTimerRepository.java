package org.idiot.yesslave.worktimer.repository;

import org.idiot.yesslave.worktimer.domain.VerificationCode;
import org.idiot.yesslave.worktimer.domain.WorkTimer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkTimerRepository extends JpaRepository<WorkTimer, Long> {

    @Query("SELECT wt FROM WorkTimer wt WHERE DATE(wt.checkIn) = :checkIn AND wt.code = :code")
    Optional<WorkTimer> findByCheckInAndCode(@Param("checkIn") LocalDate checkIn, @Param("code") VerificationCode code);
}
