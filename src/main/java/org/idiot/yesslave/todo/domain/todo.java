package org.idiot.yesslave.todo.domain;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.idiot.yesslave.global.jpa.AuditInformation;

import javax.persistence.*;


@Entity
@Getter
@Table(name = "TODO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class todo extends AuditInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @Comment("id는 todo 테이블의 pk 값 입니다")
    private Long id;

    @Column(name = "TODO", nullable = false)
    @Comment("todo는 오늘의 다짐을 적는 부분입니다.")
    private String todo;

    @Column(name = "TODO_CHECK", nullable = false)
    @Comment("todoCheck는 실행 여부를 체크하는 부분입니다.")
    private boolean todoCheck = false;

    @Column(name = "DELETE", nullable = false)
    @Comment("delete는 삭제 여부를 체크하는 부분입니다.")
    private boolean delete = false;


    @Builder
    public todo(String todo) {
        this.todo = todo;
    }


    public void changeCheck(boolean todoCheck) {
        this.todoCheck = todoCheck;
    }

    public void update(String todo) {
        this.todo = todo;
    }
    public void delete (boolean delete) {
        this.delete = delete;
    }

}
