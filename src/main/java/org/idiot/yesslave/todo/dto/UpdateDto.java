package org.idiot.yesslave.todo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateDto {
    private String todo;

    public UpdateDto(String todo) {
        this.todo = todo;
    }
}
