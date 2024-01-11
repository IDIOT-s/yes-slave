package org.idiot.yesslave.todo.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "TODO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TODO", nullable = false)
    private String todo;

    @Column(name = "REGISTER_DATE", nullable = false)
    private LocalDateTime registerDate;

    @Column(name = "UPDATE_DATE", nullable = true)
    private LocalDateTime updateDate;

    @Column(name = "TODO_CHECK", nullable = false)
    private boolean todoCheck = false;

    @Column(name = "delete", nullable = false)
    private boolean delete = false;


    @Builder
    public todo(String todo, LocalDateTime registerDate){
        this.todo = todo;
        this.registerDate = registerDate;
    }


    public void changeCheck(boolean todoCheck){
        this.todoCheck = todoCheck;
    }

    public void update(String todo, LocalDateTime updateDate){
        this.todo = todo;
        this.updateDate = updateDate;
    }


}