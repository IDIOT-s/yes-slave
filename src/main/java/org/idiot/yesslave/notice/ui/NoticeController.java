package org.idiot.yesslave.notice.ui;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.notice.application.NoticeService;
import org.idiot.yesslave.notice.domain.Notice;
import org.idiot.yesslave.notice.dto.NoticeSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping("/notice")
    public ResponseEntity<Void> registerNotice(@RequestBody NoticeSaveRequest request){
        Notice notice = Notice.createNotice(request.getTitle(), request.getContent());
        Long response = noticeService.registerNotice(notice);

        // body: ?
        return ResponseEntity.created(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response)
                    .toUri())
                .build();
    }
}
