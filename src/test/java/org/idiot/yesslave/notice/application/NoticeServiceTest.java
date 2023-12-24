package org.idiot.yesslave.notice.application;

import org.assertj.core.api.Assertions;
import org.idiot.yesslave.notice.dto.NoticeDto;
import org.idiot.yesslave.notice.reposiotory.NoticeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeServiceTest {
    @Autowired NoticeService noticeService;


    @Test
    void findAll() {
        List<NoticeDto> all = noticeService.findAll();
        Assertions.assertThat(all).isEmpty();
    }
}