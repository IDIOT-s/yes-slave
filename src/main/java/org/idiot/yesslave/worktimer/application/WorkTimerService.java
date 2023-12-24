package org.idiot.yesslave.worktimer.application;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.worktimer.domain.VerificationCode;
import org.idiot.yesslave.worktimer.domain.WorkTimer;
import org.idiot.yesslave.worktimer.repository.WorkTimerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WorkTimerService {
    private final WorkTimerRepository workTimerRepository;

    @Transactional
    public VerificationCode registerTimer(LocalDateTime checkIn) {
        VerificationCode code = createVerificationCode(checkIn.toLocalDate(), VerificationCode.create());

        return workTimerRepository.save(WorkTimer.registerOf(checkIn, code))
                .getCode();
    }

    /**
     * 새로운 확인코드를 생성합니다.
     * 동일 일자에 동일 확인 코드가 있는지 확인합니다.
     */
    private VerificationCode createVerificationCode(LocalDate targetDate, VerificationCode code) {
        boolean isExistence = workTimerRepository.findByCheckInAndCode(targetDate, code)
                .isPresent();

        if(isExistence) {
            code = createVerificationCode(targetDate, VerificationCode.create());
        }

        return code;
    }
}
