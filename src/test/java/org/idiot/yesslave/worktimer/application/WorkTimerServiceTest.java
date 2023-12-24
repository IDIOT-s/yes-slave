package org.idiot.yesslave.worktimer.application;

import org.idiot.yesslave.worktimer.domain.AuthenticateCodeGenerator;
import org.idiot.yesslave.worktimer.domain.TestVerificationCodeStrategy;
import org.idiot.yesslave.worktimer.domain.VerificationCode;
import org.idiot.yesslave.worktimer.domain.WorkTimer;
import org.idiot.yesslave.worktimer.repository.WorkTimerRepository;
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

    @Test
    void test() {
        final LocalDateTime checkIn = LocalDateTime.of(2023, 1, 1, 12, 30, 0);
        final AuthenticateCodeGenerator authenticateCodeStrategy = new TestVerificationCodeStrategy(new ArrayList<>(Arrays.asList("aaaa", "bbbb")));

        final VerificationCode firstCode = VerificationCode.manual("aaaa");
        final VerificationCode resultCode = VerificationCode.manual("bbbb");
        final WorkTimer workTimer = WorkTimer.registerOf(checkIn, resultCode);


        given(workTimerRepository.findByCheckInAndCode(checkIn.toLocalDate(), firstCode))
                .willReturn(Optional.of(WorkTimer.registerOf(checkIn, firstCode)));


        given(workTimerRepository.save(argThat((it) -> it.getCode().equals(resultCode))))
                .willReturn(WorkTimer.registerOf(checkIn, resultCode));

        //when
        VerificationCode result = workTimerService.registerTimer(checkIn, authenticateCodeStrategy);


        //then
        assertThat(result).isEqualTo(workTimer.getCode());
    }

//    @Nested
//    @DisplayName("")
//    class registerTimer {
//        @Test
//        @DisplayName("")
//        void success() {
//            //given
//            final LocalDateTime checkIn = LocalDateTime.of(2023, 1, 1, 12, 30, 0);
//            final VerificationCode expectedCode = mock()
//            final WorkTimer expectedEntity = WorkTimer.registerOf(checkIn, expectedCode);
//
//            given(workTimerRepository.findByCheckInAndCode(checkIn.toLocalDate(), any(VerificationCode.class)))
//                    .willReturn(Optional.empty());
//
//            given(workTimerRepository.save(any(WorkTimer.class)))
//                    .willReturn(expectedEntity);
//
//            //when
//            VerificationCode result = workTimerService.registerTimer(checkIn);
//
//            //then
//            assertThat(result).isEqualTo(expectedEntity.getCode());
//        }
//
//        @Test
//        @DisplayName("")
//        void success2() {
//            //given
//            final LocalDateTime checkIn = LocalDateTime.of(2023, 1, 1, 12, 30, 0);
//            final VerificationCode expectedCode = new VerificationCode("123456");
//            final WorkTimer expectedEntity = WorkTimer.registerOf(checkIn, expectedCode);
//
//            when(workTimerRepository.findByCheckInAndCode(eq(checkIn.toLocalDate()), eq(expectedCode)))
//                    .thenReturn(Optional.of(WorkTimer.registerOf(checkIn, expectedCode)));
//
//            //when
//            VerificationCode result = workTimerService.registerTimer(checkIn);
//
//            //then
//            assertThat(result).isNotEqualTo(expectedEntity.getCode());
//        }
//    }

}
