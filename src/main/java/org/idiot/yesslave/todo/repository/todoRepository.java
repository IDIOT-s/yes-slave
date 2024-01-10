package org.idiot.yesslave.todo.repository;

import org.idiot.yesslave.todo.domain.todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface todoRepository extends JpaRepository<todo,Long>{
}
