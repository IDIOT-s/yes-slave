package org.idiot.yesslave.todo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class saveDto {
    private String todo;

    @Builder
    public saveDto(String todo){
        this.todo = todo;
    }
}
