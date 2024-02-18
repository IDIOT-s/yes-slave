package org.idiot.yesslave.notice.ui;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.notice.application.NoticeService;
import org.idiot.yesslave.notice.dto.NoticeFindResponse;
import org.idiot.yesslave.notice.dto.NoticeSaveRequest;
import org.idiot.yesslave.notice.dto.NoticeUpdateRequest;
import org.idiot.yesslave.notice.dto.NoticeUpdateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<Void> registerNotice(@RequestBody @Valid NoticeSaveRequest request) {
        Long response = noticeService.registerNotice(request);

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(response)
                        .toUri())
                .build();
    }

    @GetMapping
    public ResponseEntity<List<NoticeFindResponse>> findAllNotice() {
        List<NoticeFindResponse> allNotice = noticeService.findAllNotice();
        return ResponseEntity.ok().body(allNotice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeFindResponse> findNotice(@PathVariable Long id) {
        NoticeFindResponse notice = noticeService.findNotice(id);
        return ResponseEntity.ok()
                .body(notice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeUpdateResponse> updateNotice(@PathVariable Long id, @RequestBody @Valid NoticeUpdateRequest request) {
        NoticeUpdateResponse updateNotice = noticeService.updateNotice(id, request);
        return ResponseEntity.ok()
                .location(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .build()
                        .toUri())
                .body(updateNotice);
    }

    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
    }
}
