package org.idiot.yesslave.notice.application;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.idiot.yesslave.notice.domain.Notice;
import org.idiot.yesslave.notice.dto.NoticeSaveRequest;
import org.idiot.yesslave.notice.repository.NoticeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NoticeServiceTest {
    @Autowired
    NoticeService noticeService;
    @Autowired
    NoticeRepository noticeRepository;

    @Nested
    @DisplayName("공지 저장할 때")
    class noticeRegister {
        final String title = "test title";
        final String content = "test content";

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
            softAssertions.assertThat(findNotice.getTitle()).isEqualTo("test title");
            softAssertions.assertThat(findNotice.getContent()).isEqualTo("test content");
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

}
