package org.idiot.yesslave.todo.repository;

import org.idiot.yesslave.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long>{
}
