package org.idiot.yesslave.notice.application;

import org.assertj.core.api.Assertions;
import org.idiot.yesslave.notice.domain.Notice;
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
    @DisplayName(value="공지 저장")
    class noticeRegister {
        @Test
        @DisplayName("정상적으로 저장")
        void saveNotice() {
            // given
            Notice notice = Notice.createNotice("test title", "test content");
            // when
            Long registerId = noticeService.registerNotice(notice);
            Notice findNotice = noticeRepository.findById(registerId).get();

            // then
            Assertions.assertThat(findNotice).isNotNull();
            Assertions.assertThat(findNotice.getTitle()).isEqualTo("test title");
            Assertions.assertThat(findNotice.getContent()).isEqualTo("test content");
        }

        @Test
        @DisplayName("오류 발생(제목이 없을 경우) ")
        void saveNoticeWithNullTitle() {
            //given
            Notice notice = Notice.createNotice(null, "test content");
            //when
            //then
            Assertions.assertThatThrownBy(() -> noticeRepository.save(notice)).isInstanceOf(RuntimeException.class);
        }
    }

}