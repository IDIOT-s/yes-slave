package org.idiot.yesslave.worktimer.ui;

import org.idiot.yesslave.worktimer.application.WorkTimerService;
import org.idiot.yesslave.worktimer.domain.RandomCodeGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest
@ExtendWith(SpringExtension.class)
class WorkTimerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @MockBean
    private WorkTimerService workTimerService;

    final String baseUrl = "/timer";

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Nested
    @DisplayName("타이머 등록(출근) 할 때,")
    class timerCreate {
        @Test
        @DisplayName("성공적으로 저장합니다.")
        void success() throws Exception {
            //given
            final Long expectedId = 1L;

            given(workTimerService.registerTimer(any(LocalDateTime.class), any(RandomCodeGenerator.class)))
                    .willReturn(expectedId);

            //when
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();

            //TODO: 조회 기능 생성 되면 값 검증 필요
            assertThat(mvcResult.getResponse().getHeader("Location")).isNotNull();
        }
    }
}
