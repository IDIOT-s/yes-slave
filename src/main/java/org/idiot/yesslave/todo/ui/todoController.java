package org.idiot.yesslave.todo.ui;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.todo.application.todoService;
import org.idiot.yesslave.todo.dto.saveDto;
import org.idiot.yesslave.todo.dto.updateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class todoController {

    private final todoService todoService;

    @PostMapping
    public  ResponseEntity todoSave(@RequestBody saveDto saveDto) {
        todoService.save(saveDto);
        return ResponseEntity.ok("Todo save successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity todoUpdate(@PathVariable Long id, @RequestBody updateDto updateDto) {
        todoService.update(id, updateDto);
        return ResponseEntity.ok("Todo updated successfully");
    }

    @PostMapping("/{id}")
    public ResponseEntity changeCheck(@PathVariable Long id) {
        todoService.changeCheck(id);
        return ResponseEntity.ok("Todo change successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok("Todo delete successfully");
    }

}
