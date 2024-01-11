package org.idiot.yesslave.todo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class updateDto {
    private String todo;

    public updateDto(String todo){
        this.todo = todo;
    }
}
