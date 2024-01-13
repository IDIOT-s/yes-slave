package org.idiot.yesslave.worktimer.application;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.worktimer.domain.AuthenticateCodeGenerator;
import org.idiot.yesslave.worktimer.domain.VerificationCode;
import org.idiot.yesslave.worktimer.domain.WorkTimer;
import org.idiot.yesslave.worktimer.repository.WorkTimerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WorkTimerService {

    private final WorkTimerRepository workTimerRepository;

    @Transactional
    public Long registerTimer(LocalDateTime checkIn, AuthenticateCodeGenerator authenticateCodeGenerator) {

        VerificationCode code = VerificationCode.create(authenticateCodeGenerator);
        boolean isExistence = workTimerRepository.findByCheckInAndCode(checkIn.toLocalDate(), code).isPresent();

        while (isExistence) {
            code = VerificationCode.create(authenticateCodeGenerator);
            isExistence = workTimerRepository.findByCheckInAndCode(checkIn.toLocalDate(), code).isPresent();
        }

        return workTimerRepository.save(WorkTimer.registerOf(checkIn, code))
                .getId();
    }
}
