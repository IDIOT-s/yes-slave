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

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository ;

    @Transactional
    public void save(SaveDto saveDto) {
        todoRepository.save(todo.builder()
                .todo(saveDto.getTodo())
                .build());
    }

    @Transactional
    public void update(Long id, UpdateDto updateDto) {
        todo todo = existId(id);
        checkDeleteStatus(todo);
        todo.update(updateDto.getTodo());
    }

    @Transactional
    public void changeCheck(Long id) {
        todo todo = existId(id);
        checkDeleteStatus(todo);
        todo.changeCheck(!todo.isTodoCheck());
    }

    @Transactional
    public void delete(Long id) {
        todo todo = existId(id);
        checkDeleteStatus(todo);
        todo.delete(true);
    }

    private void checkDeleteStatus(todo todo) {
        if (todo.isDelete()) {
            throw new TodoIdHandler(ErrorCode.ID_DELETE);
        }
    }

    public todo existId(Long id) {
        todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoIdHandler(ErrorCode.ID_NOT_FOUND));
        return todo;
    }

}
