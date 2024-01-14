package org.idiot.yesslave.introduce.application;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.introduce.domain.Introduce;
import org.idiot.yesslave.introduce.domain.IntroduceSaveRequest;
import org.idiot.yesslave.introduce.repository.IntroduceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IntroduceService {

    private final IntroduceRepository introduceRepository;

    @Transactional
    public Long registerIntroduce(IntroduceSaveRequest request) {
        return introduceRepository.save(Introduce.registerOf(request))
            .getId();
    }
}
