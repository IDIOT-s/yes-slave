package org.idiot.yesslave.todo.application;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.global.exception.BusinessExceptionHandler;
import org.idiot.yesslave.global.exception.errorCode;
import org.idiot.yesslave.todo.domain.todo;
import org.idiot.yesslave.todo.dto.saveDto;
import org.idiot.yesslave.todo.dto.updateDto;
import org.idiot.yesslave.todo.repository.todoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional

public class todoService {
    private final todoRepository todoRepository ;

    // 저장
    public void save(saveDto saveDto){
        todoRepository.save(todo.builder()
                .todo(saveDto.getTodo())
                .registerDate(time())
                .build());
    }


    // 수정
    public void update(Long id, updateDto updateDto){
        todo todo = existId(id);
        deleteStatus(todo);
        todo.update(updateDto.getTodo(), time());
    }

    // 체크박스 true,false
    public void changeCheck(Long id){
        todo todo = existId(id);
        deleteStatus(todo);
        todo.changeCheck(!todo.isTodoCheck());
    }

    public void delete(Long id){
        todo todo = existId(id);
        deleteStatus(todo);
        todo.delete(true);
    }

    private static void deleteStatus(todo todo) {
        if (todo.isDelete()) throw new BusinessExceptionHandler(errorCode.ID_DELETE);
    }
    public todo existId(Long id) {
        Optional<todo> op = todoRepository.findById(id);
        todo todo = op.orElseThrow(() -> new BusinessExceptionHandler(errorCode.ID_NOT_FOUND));
        return todo;
    }
    private LocalDateTime time (){
        LocalDateTime time = LocalDateTime.now();
        return time;
    }



}
