package org.idiot.yesslave.worktimer.application;

import org.idiot.yesslave.worktimer.domain.AuthenticateCodeGenerator;
import org.idiot.yesslave.worktimer.domain.RandomCodeGenerator;
import org.idiot.yesslave.worktimer.domain.TestVerificationCodeStrategy;
import org.idiot.yesslave.worktimer.domain.VerificationCode;
import org.idiot.yesslave.worktimer.domain.WorkTimer;
import org.idiot.yesslave.worktimer.repository.WorkTimerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class WorkTimerServiceTest {
    @InjectMocks
    private WorkTimerService workTimerService;

    @Mock
    private WorkTimerRepository workTimerRepository;

    @Nested
    @DisplayName("타이머를 등록 할 때,")
    class registerTimer {
        @Test
        @DisplayName("겹치는 코드가 없으면 저장에 성공한다.")
        void success() {
            final LocalDateTime checkIn = LocalDateTime.of(2023, 1, 1, 12, 30, 0);
            final AuthenticateCodeGenerator authenticateCodeStrategy = new RandomCodeGenerator();

            final VerificationCode code = VerificationCode.manual("123456");

            final WorkTimer expectedWorkTime = WorkTimer.registerOf(checkIn, code);

            given(workTimerRepository.findByCheckInAndCode(eq(checkIn.toLocalDate()), any(VerificationCode.class)))
                    .willReturn(Optional.empty());

            given(workTimerRepository.save(any(WorkTimer.class)))
                    .willReturn(expectedWorkTime);

            //when
            VerificationCode result = workTimerService.registerTimer(checkIn, authenticateCodeStrategy);

            //then
            assertThat(result).isNotNull();
        }
    }
}
