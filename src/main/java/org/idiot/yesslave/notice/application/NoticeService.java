package org.idiot.yesslave.notice.application;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.notice.domain.Notice;
import org.idiot.yesslave.notice.dto.NoticeSaveRequest;
import org.idiot.yesslave.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public Long registerNotice(NoticeSaveRequest noticeSaveRequest) {
        System.out.println(noticeSaveRequest.getContent());
        Notice registerNotice = noticeRepository.save(Notice.createNotice(noticeSaveRequest));
        return registerNotice.getId();
    }
}
