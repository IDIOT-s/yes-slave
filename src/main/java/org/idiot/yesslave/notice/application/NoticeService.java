package org.idiot.yesslave.notice.application;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.global.exception.NotFoundException;
import org.idiot.yesslave.notice.domain.Notice;
import org.idiot.yesslave.notice.dto.NoticeFindResponse;
import org.idiot.yesslave.notice.dto.NoticeSaveRequest;
import org.idiot.yesslave.notice.dto.NoticeUpdateRequest;
import org.idiot.yesslave.notice.dto.NoticeUpdateResponse;
import org.idiot.yesslave.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<NoticeFindResponse> findAllNotice() {
        List<Notice> allNotice = noticeRepository.findAll();
        return allNotice.stream().map(NoticeFindResponse::of).toList();
    }

    @Transactional(readOnly = true)
    public NoticeFindResponse findNotice(Long id) {
        Notice findNotice = noticeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        return NoticeFindResponse.of(findNotice);
    }

    @Transactional
    public NoticeUpdateResponse updateNotice(Long id, NoticeUpdateRequest request) {
        Notice findNotice = noticeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        findNotice.update(request);
        return NoticeUpdateResponse.of(findNotice);
    }

}
