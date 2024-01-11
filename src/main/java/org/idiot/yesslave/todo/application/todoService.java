package org.idiot.yesslave.todo.application;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.todo.domain.todo;
import org.idiot.yesslave.todo.dto.saveDto;
import org.idiot.yesslave.todo.repository.todoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional

public class todoService {
    private final todoRepository todoRepository ;
    private LocalDateTime time = LocalDateTime.now();
    // 저장
    public void save(saveDto saveDto){
        todoRepository.save(todo.builder()
                .todo(saveDto.getTodo())
                .registerDate(time)
                .build());
    }


    // 수정
    public void update(){}
    
    // 체크박스 true,false
    public void changeStatus(){}

}
