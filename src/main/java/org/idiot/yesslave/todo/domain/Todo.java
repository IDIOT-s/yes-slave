package org.idiot.yesslave.todo.domain;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.idiot.yesslave.global.jpa.AuditInformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Getter
@Table(name = "TODO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends AuditInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TODO", nullable = false)
    @Comment("다짐")
    private String todo;

    @Column(name = "TODO_CHECK", nullable = false)
    @Comment("실행여부")
    private boolean todoCheck = false;

    @Column(name = "DELETE", nullable = false)
    @Comment("삭제여부")
    private boolean delete = false;


    @Builder
    public Todo(String todo) {
        this.todo = todo;
    }


    public void changeCheck(boolean todoCheck) {
        this.todoCheck = todoCheck;
    }

    public void update(String todo) {
        this.todo = todo;
    }
    public void delete () {
        delete = true;
    }

}
