package org.idiot.yesslave.todo.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class saveDto {
    private String todo;

    @Builder
    public saveDto(String todo){
        this.todo = todo;
    }
}
