package org.idiot.yesslave.notice.application;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.notice.domain.Notice;
import org.idiot.yesslave.notice.dto.NoticeDto;
import org.idiot.yesslave.notice.exception.NoSuchNoticeException;
import org.idiot.yesslave.notice.reposiotory.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    public NoticeDto findById(Long id){
        Optional<Notice> findOptionalNotice = noticeRepository.findById(id);
        Notice findNotice = findOptionalNotice.orElseThrow(NoSuchNoticeException::new);

        return noticeDtoMapper(findNotice);
    }

    public List<NoticeDto> findAll(){
        return noticeRepository.findAll()
                .stream()
                .map(this::noticeDtoMapper)
                .collect(Collectors.toList());
    }

    private NoticeDto noticeDtoMapper(Notice findNotice) {
        NoticeDto noticeDto = new NoticeDto();
        noticeDto.setId(findNotice.getId());
        noticeDto.setTitle(findNotice.getTitle());
        noticeDto.setContent(findNotice.getContent());
        return noticeDto;
    }
}
