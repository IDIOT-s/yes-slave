package org.idiot.yesslave.notice.application;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.idiot.yesslave.global.exception.NotFoundException;
import org.idiot.yesslave.notice.domain.Notice;
import org.idiot.yesslave.notice.dto.NoticeFindResponse;
import org.idiot.yesslave.notice.dto.NoticeSaveRequest;
import org.idiot.yesslave.notice.repository.NoticeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class NoticeServiceTest {
    @Autowired
    NoticeService noticeService;
    @Autowired
    NoticeRepository noticeRepository;
    final String title = "test title";
    final String content = "test content";
    final String newTitle = "new title";
    final String newContent = "new Content";
    final Long failId = 2L;

    @Nested
    @DisplayName("공지 저장할 때")
    class NoticeRegister {
        @Test
        @DisplayName("정상적으로 저장합니다.")
        void saveNotice() {
            // given
            NoticeSaveRequest noticeSaveRequest = NoticeSaveRequest.builder()
                    .title(title)
                    .content(content)
                    .build();

            // when
            Long registerId = noticeService.registerNotice(noticeSaveRequest);
            Notice findNotice = noticeRepository.findById(registerId).get();

            // then
            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(findNotice).isNotNull();
            softAssertions.assertThat(findNotice.getTitle()).isEqualTo(title);
            softAssertions.assertThat(findNotice.getContent()).isEqualTo(content);
            softAssertions.assertAll();
        }

        @Test
        @DisplayName("제목이 없어 오류가 발생합니다.")
        void saveNoticeWithNullTitle() {
            //given
            NoticeSaveRequest noticeSaveRequest = NoticeSaveRequest.builder()
                    .content(content)
                    .build();
            Notice notice = Notice.createNotice(noticeSaveRequest);
            //expected
            Assertions.assertThatThrownBy(() -> noticeRepository.save(notice)).isInstanceOf(RuntimeException.class);
        }
    }

    @Nested
    @DisplayName("공지를 조회할 때")
    class NoticeFind {
        @Test
        @DisplayName("전체 조회에 성공합니다")
        void findAllNoticeSuccess() {
            // given
            NoticeSaveRequest saveRequest = NoticeSaveRequest.builder()
                    .title(title)
                    .content(content)
                    .build();
            Notice saveNotice1 = noticeRepository.save(Notice.createNotice(saveRequest));
            Notice saveNotice2 = noticeRepository.save(Notice.createNotice(saveRequest));

            // when
            List<NoticeFindResponse> allNotice = noticeService.findAllNotice();

            // then
            Assertions.assertThat(allNotice.size()).isEqualTo(2);
        }

        @Test
        @DisplayName("조회에 성공합니다")
        void findNoticeSuccess() {
            //given
            NoticeSaveRequest saveRequest = NoticeSaveRequest.builder()
                    .title(title)
                    .content(content)
                    .build();
            Notice saveNotice = noticeRepository.save(Notice.createNotice(saveRequest));

            //when
            NoticeFindResponse findNoticeResponse = noticeService.findNotice(saveNotice.getId());

            //then
            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(saveNotice.getContent()).isEqualTo(findNoticeResponse.getContent());
            softAssertions.assertThat(saveNotice.getTitle()).isEqualTo(findNoticeResponse.getTitle());
            softAssertions.assertAll();
        }

        @Test
        @DisplayName("id에 맞는 데이터가 없어 조회에 실패합니다")
        void findNoticeFailByNotFound() {
            // given
            NoticeSaveRequest saveRequest = NoticeSaveRequest.builder()
                    .title(title)
                    .content(content)
                    .build();
            Notice saveNotice = noticeRepository.save(Notice.createNotice(saveRequest));

            // when
            Assertions.assertThatThrownBy(() -> noticeService.findNotice(failId))
                    .isInstanceOf(NotFoundException.class);
        }
    }

}
