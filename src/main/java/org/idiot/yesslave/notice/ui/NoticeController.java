package org.idiot.yesslave.notice.ui;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.notice.application.NoticeService;
import org.idiot.yesslave.notice.dto.NoticeSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping("/notice")
    public ResponseEntity<Void> registerNotice(@RequestBody @Valid NoticeSaveRequest request) {
        Long response = noticeService.registerNotice(request);

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(response)
                        .toUri())
                .build();
    }
}
