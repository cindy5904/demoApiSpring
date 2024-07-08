package org.example.demospringapi.repository;

import org.example.demospringapi.entity.TodoList;
import org.springframework.data.repository.CrudRepository;

public interface TodoListRepository extends CrudRepository<TodoList, Long> {
}
