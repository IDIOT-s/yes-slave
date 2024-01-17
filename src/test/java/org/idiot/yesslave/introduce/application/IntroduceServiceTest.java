package org.idiot.yesslave.introduce.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.idiot.yesslave.global.exception.NotFoundException;
import org.idiot.yesslave.introduce.domain.Introduce;
import org.idiot.yesslave.introduce.domain.IntroduceResponse;
import org.idiot.yesslave.introduce.domain.IntroduceSaveRequest;
import org.idiot.yesslave.introduce.repository.IntroduceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Nested
    @DisplayName("자기소개를 조회 할 때")
    class findIntroduce {

        @Test
        @DisplayName("성공적으로 자기소개를 가져온다.")
        void success() {
            //given
            final Long id = 1L;

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

            final Introduce expectedIntroduce = new Introduce(
                id
                , name
                , email
                , imageUrl
                , bio
                , githubUrl
                , company
                , companyUrl
                , siteUrl
                , snsUrl1
                , snsUrl2
            );

            given(introduceRepository.findById(id)).willReturn(Optional.of(expectedIntroduce));

            //when
            IntroduceResponse result = introduceService.findIntroduce(id);

            //then
            assertSoftly(it -> {
                it.assertThat(result.getName()).isEqualTo(name);
                it.assertThat(result.getEmail()).isEqualTo(email);
                it.assertThat(result.getImageUrl()).isEqualTo(imageUrl);
                it.assertThat(result.getBio()).isEqualTo(bio);
                it.assertThat(result.getGithubUrl()).isEqualTo(githubUrl);
                it.assertThat(result.getCompany()).isEqualTo(company);
                it.assertThat(result.getCompanyUrl()).isEqualTo(companyUrl);
                it.assertThat(result.getSiteUrl()).isEqualTo(siteUrl);
                it.assertThat(result.getSnsUrl1()).isEqualTo(snsUrl1);
                it.assertThat(result.getSnsUrl2()).isEqualTo(snsUrl2);
            });
        }

        @Test
        @DisplayName("없는 데이터를 요청하여 오류가 발생한다.")
        void notFoundException() {
            //given
            final Long id = 99L;

            given(introduceRepository.findById(id)).willThrow(new NotFoundException());

            //when & then
            assertThrows(NotFoundException.class, () -> introduceService.findIntroduce(id));
        }
    }
}
