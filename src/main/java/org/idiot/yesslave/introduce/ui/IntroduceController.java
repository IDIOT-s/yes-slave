package org.idiot.yesslave.introduce.ui;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.introduce.application.IntroduceService;
import org.idiot.yesslave.introduce.domain.IntroduceSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RequestMapping("/introduce")
@RestController
@RequiredArgsConstructor
public class IntroduceController {

    private final IntroduceService introduceService;

    @PostMapping
    public ResponseEntity<Void> introduceCreate(@RequestBody @Valid IntroduceSaveRequest request) {
        Long id = introduceService.registerIntroduce(request);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(id)
                        .toUri())
                .build();
    }
}
