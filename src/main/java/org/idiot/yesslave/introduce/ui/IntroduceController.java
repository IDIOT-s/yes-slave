package org.idiot.yesslave.introduce.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.introduce.application.IntroduceService;
import org.idiot.yesslave.introduce.domain.IntroduceResponse;
import org.idiot.yesslave.introduce.domain.IntroduceSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.zalando.problem.Problem;

@Validated
@RequestMapping("/introduce")
@RestController
@RequiredArgsConstructor
public class IntroduceController {

    private final IntroduceService introduceService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "저장 성공")
    @Operation(summary = "자기소개 등록", description = "자기소개를 저장합니다.")
    public ResponseEntity<Void> introduceCreate(@RequestBody @Valid IntroduceSaveRequest request) {
        Long id = introduceService.registerIntroduce(request);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(id)
                        .toUri())
                .build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "자기소개 조회", description = "특정 id에 대한 자기소개를 조회합니다.")
    public IntroduceResponse getIntroduce(@PathVariable @Min(0) long id) {
        return introduceService.findIntroduce(id);
    }
}
