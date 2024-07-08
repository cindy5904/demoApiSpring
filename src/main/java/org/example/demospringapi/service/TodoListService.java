package org.example.demospringapi.service;

import org.example.demospringapi.dto.TodoListDoPost;
import org.example.demospringapi.entity.TodoList;
import org.example.demospringapi.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoListService {
    @Autowired
    private TodoListRepository todoListRepository;

    public TodoList getById(Long id) {
        return todoListRepository.findById(id).orElse(null);
    }

    public TodoList create (TodoListDoPost todoListDoPost){
       TodoList toDoListcreated = TodoList.builder()
                .titre(todoListDoPost.getTitre())
               .description(todoListDoPost.getDescription())
               .date(LocalDate.parse(todoListDoPost.getDate()))
               .isvalidate(todoListDoPost.isIsvalidate())
                .build();
        return todoListRepository.save(toDoListcreated);
    }

    public List<TodoList> getAll (){
        return (List<TodoList>) todoListRepository.findAll();
    }

    public TodoList update(Long id, TodoListDoPost todoListDoPost) {
        return todoListRepository.findById(id).map(todoList -> {
            todoList.setTitre(todoListDoPost.getTitre());
            todoList.setDescription(todoListDoPost.getDescription());
            todoList.setDate(LocalDate.parse(todoListDoPost.getDate()));
            todoList.setIsvalidate(todoListDoPost.isIsvalidate());
            return todoListRepository.save(todoList);
        }).orElse(null);
    }

    public boolean delete(Long id) {
        if (todoListRepository.existsById(id)) {
            todoListRepository.deleteById(id);
            return true;
        }
        return false;
    }



    }
