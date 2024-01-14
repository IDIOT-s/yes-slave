package org.idiot.yesslave.todo.application;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.global.exception.TodoIdHandler;
import org.idiot.yesslave.global.exception.ErrorCode;
import org.idiot.yesslave.todo.domain.todo;
import org.idiot.yesslave.todo.dto.SaveDto;
import org.idiot.yesslave.todo.dto.UpdateDto;
import org.idiot.yesslave.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {
    private final TodoRepository todoRepository ;

    // 저장
    public void save(SaveDto saveDto) {
        todoRepository.save(todo.builder()
                .todo(saveDto.getTodo())
                .registerDate(time())
                .build());
    }

    // 수정
    public void update(Long id, UpdateDto updateDto) {
        todo todo = existId(id);
        deleteStatus(todo);
        todo.update(updateDto.getTodo(), time());
    }

    // 체크박스 true,false
    public void changeCheck(Long id) {
        todo todo = existId(id);
        deleteStatus(todo);
        todo.changeCheck(!todo.isTodoCheck());
    }

    // 논리적 삭제
    public void delete(Long id) {
        todo todo = existId(id);
        deleteStatus(todo);
        todo.delete(true);
    }

    // 삭제 상태 확인
    private static void deleteStatus(todo todo) {
        if (todo.isDelete()) throw new TodoIdHandler(ErrorCode.ID_DELETE);
    }

    //id 존재 확인
    public todo existId(Long id) {
        Optional<todo> op = todoRepository.findById(id);
        todo todo = op.orElseThrow(() -> new TodoIdHandler(ErrorCode.ID_NOT_FOUND));
        return todo;
    }

    //시간 갱신
    private LocalDateTime time() {
        LocalDateTime time = LocalDateTime.now();
        return time;
    }



}
