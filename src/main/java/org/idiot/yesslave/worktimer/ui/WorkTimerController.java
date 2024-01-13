package org.idiot.yesslave.worktimer.ui;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.worktimer.application.WorkTimerService;
import org.idiot.yesslave.worktimer.domain.RandomCodeGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;

@RequestMapping("/timer")
@RestController
@RequiredArgsConstructor
class WorkTimerController {
    private final WorkTimerService workTimerService;

    @PostMapping
    public ResponseEntity<Void> timerCreate() {
        Long response = workTimerService.registerTimer(LocalDateTime.now(), new RandomCodeGenerator());

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(response)
                        .toUri())
                .build();
    }
}
