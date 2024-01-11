package org.idiot.yesslave.notice.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.idiot.yesslave.notice.application.NoticeService;
import org.idiot.yesslave.notice.domain.Notice;
import org.idiot.yesslave.notice.dto.NoticeSaveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
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
    final String baseUrl="/notice";

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    @DisplayName(value="공지 생성 성공")
    void createNotice() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post(baseUrl)
                        .content(asJsonString(NoticeSaveRequest.builder()
                                .title("test title")
                                .content("test content")
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        given(noticeService.registerNotice(Notice.createNotice("test title", "test content"))).willReturn(1L);
    }

    static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}