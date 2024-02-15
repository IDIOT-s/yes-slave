package org.idiot.yesslave.notice.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.idiot.yesslave.notice.application.NoticeService;
import org.idiot.yesslave.notice.dto.NoticeSaveRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootTest
@AutoConfigureMockMvc
class NoticeControllerTest {
    @MockBean
    NoticeService noticeService;
    @Autowired
    private MockMvc mockMvc;
    final String baseUrl = "/notice";
    final String title = "test title";
    final String content = "test content";

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Nested
    @DisplayName("공지를 생성할 때")
    class noticeCreate {
        @Test
        @DisplayName("성공합니다")
        void createNotice() throws Exception {
            String requestBody = asJsonString(NoticeSaveRequest.builder()
                    .title(title)
                    .content(content)
                    .build());

            mockMvc.perform(
                            MockMvcRequestBuilders.post(baseUrl)
                                    .content(requestBody)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();
        }

        @Test
        @DisplayName("제목이 없어 실패합니다.")
        void createNoticeFail() throws Exception {
            String requestBody = asJsonString(NoticeSaveRequest.builder()
                    .content(content)
                    .build());

            mockMvc.perform(
                            MockMvcRequestBuilders.post(baseUrl)
                                    .content(requestBody)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }
    }

    static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
