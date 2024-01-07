package org.idiot.yesslave.worktimer.repository;

import org.idiot.yesslave.worktimer.domain.WorkTimer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTimerRepository extends JpaRepository<WorkTimer, Long>, WorkTimerCustomRepository {

}
