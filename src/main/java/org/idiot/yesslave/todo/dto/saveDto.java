package org.idiot.yesslave.todo.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// cannot deserialize from Object value (no delegate- or property-based Creator)에러로 인해 기본 생성자 필요
public class saveDto {
    private String todo;

    public saveDto(String todo){
        this.todo = todo;
    }
}
