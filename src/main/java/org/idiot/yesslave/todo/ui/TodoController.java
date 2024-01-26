package org.idiot.yesslave.todo.ui;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.todo.application.TodoService;
import org.idiot.yesslave.todo.dto.SaveDto;
import org.idiot.yesslave.todo.dto.UpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public  ResponseEntity todoSave(@RequestBody SaveDto saveDto) {
        todoService.save(saveDto);
        return ResponseEntity.ok("Todo save successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity todoUpdate(@PathVariable Long id, @RequestBody UpdateDto updateDto) {
        todoService.update(id, updateDto);
        return ResponseEntity.ok("Todo updated successfully");
    }

    @PostMapping("/{id}")
    public boolean changeCheck(@PathVariable Long id) {
        return todoService.changeCheck(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok("Todo delete successfully");
    }

}
