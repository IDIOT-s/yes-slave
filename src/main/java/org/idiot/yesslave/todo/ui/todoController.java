package org.idiot.yesslave.todo.ui;

import lombok.RequiredArgsConstructor;
import org.idiot.yesslave.todo.application.todoService;
import org.idiot.yesslave.todo.dto.saveDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class todoController {

    private final todoService todoService;

    @PostMapping
    public  ResponseEntity todoSave(@RequestBody saveDto saveDto){
        todoService.save(saveDto);
        return ResponseEntity.created(URI.create("/todo")).build();

    }

}
