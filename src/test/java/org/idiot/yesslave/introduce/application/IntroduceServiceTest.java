package org.idiot.yesslave.introduce.application;

import org.idiot.yesslave.introduce.domain.Introduce;
import org.idiot.yesslave.introduce.domain.IntroduceSaveRequest;
import org.idiot.yesslave.introduce.repository.IntroduceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class IntroduceServiceTest {

    @InjectMocks
    private IntroduceService introduceService;

    @Mock
    private IntroduceRepository introduceRepository;

    @Nested
    @DisplayName("자기소개를 등록할 때,")
    class registerIntroduce {

        @Test
        @DisplayName("성공적으로 등록합니다.")
        void success() {
            //given
            final String name = "김갑돌";
            final String email = "aa@bb.co.kr";
            final String imageUrl = null;
            final String bio = "안녕하세요";
            final String githubUrl = null;
            final String company = "BBQ";
            final String companyUrl = null;
            final String siteUrl = null;
            final String snsUrl1 = null;
            final String snsUrl2 = null;

            final IntroduceSaveRequest request = new IntroduceSaveRequest(
                    name,
                    email,
                    imageUrl,
                    bio,
                    githubUrl,
                    company,
                    companyUrl,
                    siteUrl,
                    snsUrl1,
                    snsUrl2
            );

            final Introduce expectedResult = new Introduce(
                    1L,
                    name,
                    email,
                    imageUrl,
                    bio,
                    githubUrl,
                    company,
                    companyUrl,
                    siteUrl,
                    snsUrl1,
                    snsUrl2
            );

            given(introduceRepository.save(any(Introduce.class)))
                    .willReturn(expectedResult);

            //when
            Long result = introduceService.registerIntroduce(request);

            //then
            assertThat(result).isNotNull();
        }
    }
}