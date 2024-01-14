package org.idiot.yesslave.introduce.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.idiot.yesslave.introduce.application.IntroduceService;
import org.idiot.yesslave.introduce.domain.IntroduceSaveRequest;
import org.idiot.yesslave.test.TestUtils;
import org.idiot.yesslave.test.fixture.TestFixtureUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(IntroduceController.class)
class IntroduceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @MockBean
    private IntroduceService introduceService;

    final String baseUrl = "/introduce";

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Nested
    @DisplayName("자기소개를 등록할 때,")
    class introduceCreate {
        @Nested
        @DisplayName("유효성 검증에 통과하여")
        class valid {
            @Test
            @DisplayName("성공적으로 저장을 합니다.")
            void success() throws Exception {
                //given
                final String requestBody = """
                        {
                          "name": "name",
                          "email": "aa@bb.co.kr",
                          "imageUrl": "aa@bb.co.kr",
                          "bio": "안녕하세요. 김갑돌입니다.",
                          "githubUrl": "https://github.com",
                          "company": "(주)치킨이맛있어요",
                          "companyUrl": "https://bbq.co.kr/",
                          "siteUrl": "https://section.blog.naver.com/BlogHome.naver",
                          "snsUrl1": "https://twitter.com/",
                          "snsUrl2": "http://linkedin.com/"
                        }
                        """;

                IntroduceSaveRequest request = TestUtils.toDto(requestBody, IntroduceSaveRequest.class);

                given(introduceService.registerIntroduce(request))
                        .willReturn(anyLong());

                //when & then
                mockMvc.perform(post(baseUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                        .andExpect(MockMvcResultMatchers.status().isCreated());
            }
        }

        @Nested
        @DisplayName("유효성 검증에 통과 못했을 때, 사유로")
        class invalid {
            @Nested
            @DisplayName("입력된 이름에서")
            class name {
                @Test
                @DisplayName("빈 값으로 들어와서 실패")
                void isEmpty() throws Exception {
                    //given
                    final String name = "";

                    final String requestBody = """
                            {
                              "name": "$name",
                              "email": "aa@bb.co.kr"
                            }
                            """.replace("$name", name);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }

                @Test
                @DisplayName("입력이 안되어서 실패")
                void isNull() throws Exception {
                    //given
                    final String requestBody = """
                            {
                              "email": "aa@bb.co.kr"
                            }
                            """;

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }

                @Test
                @DisplayName("32자 넘게 작성되어 실패")
                void isInvalidSize() throws Exception {
                    //given
                    final int size = 33;
                    final String name = TestFixtureUtils.size에_맞는_문자열_생성(size);

                    final String requestBody = """
                            {
                              "name": "$name",
                              "email": "aa@bb.co.kr"
                            }
                            """.replace("$name", name);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }
            }

            @Nested
            @DisplayName("입력된 이메일이")
            class email {
                @Test
                @DisplayName("빈 값으로 들어와서 실패")
                void isEmpty() throws Exception {
                    //given
                    final String email = "";

                    final String requestBody = """
                            {
                              "name": "김갑돌",
                              "email": "$email"
                            }
                            """.replace("$email", email);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }

                @Test
                @DisplayName("입력이 안되어서 실패")
                void isNull() throws Exception {
                    //given
                    final String requestBody = """
                            {
                              "name": "김갑돌"
                            }
                            """;

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }

                @Test
                @DisplayName("64자 넘게 작성되어 실패")
                void isInvalidSize() throws Exception {
                    //given
                    final int size = 65;
                    final String email = TestFixtureUtils.size에_맞는_문자열_생성(size);

                    final String requestBody = """
                            {
                              "name": "김갑돌",
                              "email": "$email"
                            }
                            """.replace("$email", email);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }
            }

            @Nested
            @DisplayName("입력된 프로필 이미지 URL이")
            class imageUrl {
                @Test
                @DisplayName("512 넘게 작성되어 실패")
                void isInvalidSize() throws Exception {
                    //given
                    final int size = 513;
                    final String param = TestFixtureUtils.size에_맞는_문자열_생성(size);

                    final String requestBody = """
                            {
                              "name": "김갑돌",
                              "email": "$email",
                              "imageUrl": "$imageUrl"
                            }
                            """.replace("$imageUrl", param);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }
            }

            @Nested
            @DisplayName("입력된 자기소개가")
            class bio {
                @Test
                @DisplayName("512 넘게 작성되어 실패")
                void isInvalidSize() throws Exception {
                    //given
                    final int size = 513;
                    final String param = TestFixtureUtils.size에_맞는_문자열_생성(size);

                    final String requestBody = """
                            {
                              "name": "김갑돌",
                              "email": "aa@cc.co.kr",
                              "bio": "$bio"
                            }
                            """.replace("$bio", param);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }
            }

            @Nested
            @DisplayName("입력된 Github Url이")
            class githubUrl {
                @Test
                @DisplayName("512자 넘게 작성되어 실패")
                void isInvalidSize() throws Exception {
                    //given
                    final int size = 513;
                    final String param = TestFixtureUtils.size에_맞는_문자열_생성(size);

                    final String requestBody = """
                            {
                              "name": "김갑돌",
                              "email": "aa@cc.co.kr",
                              "githubUrl": "$githubUrl"
                            }
                            """.replace("$githubUrl", param);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }
            }

            @Nested
            @DisplayName("입력된 회사명이")
            class company {
                @Test
                @DisplayName("64자 넘게 작성되어 실패")
                void isInvalidSize() throws Exception {
                    //given
                    final int size = 65;
                    final String param = TestFixtureUtils.size에_맞는_문자열_생성(size);

                    final String requestBody = """
                            {
                              "name": "김갑돌",
                              "email": "aa@cc.co.kr",
                              "company": "$company"
                            }
                            """.replace("$company", param);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }
            }

            @Nested
            @DisplayName("입력된 회사 URL이")
            class companyUrl {
                @Test
                @DisplayName("256자 넘게 작성되어 실패")
                void isInvalidSize() throws Exception {
                    //given
                    final int size = 257;
                    final String param = TestFixtureUtils.size에_맞는_문자열_생성(size);

                    final String requestBody = """
                            {
                              "name": "김갑돌",
                              "email": "aa@cc.co.kr",
                              "companyUrl": "$companyUrl"
                            }
                            """.replace("$companyUrl", param);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }
            }

            @Nested
            @DisplayName("입력된 블로그 URL이")
            class siteUrl {
                @Test
                @DisplayName("256자 넘게 작성되어 실패")
                void isInvalidSize() throws Exception {
                    //given
                    final int size = 257;
                    final String param = TestFixtureUtils.size에_맞는_문자열_생성(size);

                    final String requestBody = """
                            {
                              "name": "김갑돌",
                              "email": "aa@cc.co.kr",
                              "siteUrl": "$siteUrl"
                            }
                            """.replace("$siteUrl", param);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }
            }

            @Nested
            @DisplayName("입력된 첫번째 SNS URL이")
            class snsUrl1 {
                @Test
                @DisplayName("512자 넘게 작성되어 실패")
                void isInvalidSize() throws Exception {
                    //given
                    final int size = 513;
                    final String param = TestFixtureUtils.size에_맞는_문자열_생성(size);

                    final String requestBody = """
                            {
                              "name": "김갑돌",
                              "email": "aa@cc.co.kr",
                              "snsUrl1": "$snsUrl1"
                            }
                            """.replace("$snsUrl1", param);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }
            }

            @Nested
            @DisplayName("입력된 두번째 SNS URL이")
            class snsUrl2 {
                @Test
                @DisplayName("512자 넘게 작성되어 실패")
                void isInvalidSize() throws Exception {
                    //given
                    final int size = 513;
                    final String param = TestFixtureUtils.size에_맞는_문자열_생성(size);

                    final String requestBody = """
                            {
                              "name": "김갑돌",
                              "email": "aa@cc.co.kr",
                              "snsUrl2": "$snsUrl2"
                            }
                            """.replace("$snsUrl2", param);

                    //when & then
                    mockMvc.perform(post(baseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
                }
            }
        }
    }
}