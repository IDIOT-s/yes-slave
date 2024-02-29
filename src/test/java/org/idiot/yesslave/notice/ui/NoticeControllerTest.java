package org.idiot.yesslave.notice.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.idiot.yesslave.global.exception.NotFoundException;
import org.idiot.yesslave.notice.application.NoticeService;
import org.idiot.yesslave.notice.dto.NoticeSaveRequest;
import org.idiot.yesslave.notice.dto.NoticeUpdateRequest;
import org.idiot.yesslave.notice.dto.NoticeUpdateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(NoticeController.class)
@ExtendWith(SpringExtension.class)
class NoticeControllerTest {
    @MockBean
    NoticeService noticeService;
    @Autowired
    private MockMvc mockMvc;
    final String baseUrl = "/notice";
    final String title = "test title";
    final String content = "test content";
    final long expectId = 1L;
    final long failId = 2L;
    final String newTitle = "new title";
    final String newContent = "new content";

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Nested
    @DisplayName("공지를 생성할 때")
    class NoticeCreate {
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

    @Nested
    @DisplayName("공지를 조회할 때")
    class NoticeFind {
        @Test
        @DisplayName("전체 조회에 성공합니다")
        void findAllNotice() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(baseUrl))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("단일 조회에 성공합니다")
        void findNoticeSuccess() throws Exception {
            NoticeSaveRequest request = NoticeSaveRequest.builder()
                    .title(title)
                    .content(content)
                    .build();
            BDDMockito.given(noticeService.registerNotice(request)).willReturn(expectId);
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + String.format("/%d", expectId)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            System.out.println(mvcResult.getResponse().getContentAsString());
        }

        @Test
        @DisplayName("단일 조회에 실패합니다")
        void findNoticeFail() throws Exception {
            Mockito.doThrow(new NotFoundException()).when(noticeService).findNotice(failId);
            mockMvc.perform(
                            MockMvcRequestBuilders.get(baseUrl + "/%d", failId))
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        }
    }

    @Nested
    @DisplayName("공지를 수정할 때")
    class NoticeUpdate {
        @Test
        @DisplayName("수정에 성공합니다.")
        void updateNoticeSuccess() throws Exception {
            NoticeSaveRequest saveRequest = NoticeSaveRequest.builder()
                    .title(title)
                    .content(content)
                    .build();
            BDDMockito.given(noticeService.registerNotice(saveRequest)).willReturn(expectId);

            NoticeUpdateRequest request = NoticeUpdateRequest.builder()
                    .title(newTitle)
                    .content(newContent)
                    .build();
            String requestString = asJsonString(request);

            BDDMockito.given(noticeService.updateNotice(expectId, request)).willReturn(
                    NoticeUpdateResponse.builder()
                            .id(expectId)
                            .title(newTitle)
                            .content(newContent)
                            .build()
            );

            mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/{id}", expectId)
                            .content(requestString)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("해당 데이터가 없어 수정에 실패합니다")
        void updateNoticeFailById() throws Exception {
            NoticeUpdateRequest request = NoticeUpdateRequest.builder()
                    .title(newTitle)
                    .content(newContent)
                    .build();
            String requestString = asJsonString(request);

            BDDMockito.given(noticeService.updateNotice(failId, request))
                    .willThrow(NotFoundException.class);

            mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/{id}", failId)
                            .content(requestString)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        }

        @Test
        @DisplayName("제목이 없어 수정에 실패합니다")
        void updateNoticeFailByTitle() throws Exception {
            NoticeUpdateRequest request = NoticeUpdateRequest.builder()
                    .content(newContent)
                    .build();
            String requestString = asJsonString(request);
            mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/{id}", failId)
                            .content(requestString)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        }
    }

    @Nested
    @DisplayName("공지를 삭제할 때")
    class NoticeDelete {
        @Test
        @DisplayName("삭제에 성공합니다")
        void deleteNoticeSuccess() throws Exception {
            NoticeSaveRequest saveRequest = NoticeSaveRequest.builder()
                    .title(title)
                    .content(content)
                    .build();
            BDDMockito.given(noticeService.registerNotice(saveRequest)).willReturn(expectId);
            BDDMockito.doNothing().when(noticeService).deleteNotice(expectId);
            mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/{id}", expectId));
        }

        @Test
        @DisplayName("삭제에 실패합니다")
        void deleteNoticeFail() throws Exception {
            Mockito.doThrow(NotFoundException.class).when(noticeService).deleteNotice(failId);
            mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/{id}", failId))
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError());
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
