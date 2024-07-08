package org.example.demospringapi.controller;

import org.example.demospringapi.dto.ToDoListDoGet;
import org.example.demospringapi.dto.TodoListDoPost;
import org.example.demospringapi.entity.TodoList;
import org.example.demospringapi.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/maincontroller")
public class MainController {
    @Autowired
    private TodoListService todoListService;

    @GetMapping("/")
    public ResponseEntity<List<ToDoListDoGet>> getData() {
        List<TodoList> todoLists = todoListService.getAll();
        List<ToDoListDoGet> todoGetList = todoLists.stream()
                .map(todoList ->  new ToDoListDoGet(todoList.getId(),todoList.getTitre(), todoList.getDescription(),
                        todoList.getDate(), todoList.isIsvalidate()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(todoGetList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoListDoGet> getDataById(@PathVariable("id") long id) {
        TodoList todoList = todoListService.getById(id);
        ToDoListDoGet toDoListDoGet = new ToDoListDoGet(todoList.getId(),todoList.getTitre(), todoList.getDescription(),
                todoList.getDate(), todoList.isIsvalidate());
        return ResponseEntity.ok(toDoListDoGet);
    }
    @PostMapping("/create")
    public ResponseEntity<ToDoListDoGet> registerData(@RequestBody TodoListDoPost todoListDoPost) {
        TodoList todoList = todoListService.create(todoListDoPost);
        ToDoListDoGet toDoListDoGet = new ToDoListDoGet(todoList.getId(),todoList.getTitre(), todoList.getDescription(),
                todoList.getDate(), todoList.isIsvalidate());
        return new ResponseEntity<>(toDoListDoGet, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ToDoListDoGet> updateData(@PathVariable("id") long id,
                                                    @RequestBody TodoListDoPost todoListDoPost) {
        TodoList todoList = todoListService.update(id, todoListDoPost);
        if (todoList == null) {
            return ResponseEntity.notFound().build();
        }
        ToDoListDoGet toDoListDoGet = new ToDoListDoGet(todoList.getId(),todoList.getTitre(), todoList.getDescription(),
                todoList.getDate(), todoList.isIsvalidate());
        return ResponseEntity.ok(toDoListDoGet);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable("id") long id) {
        boolean isDeleted = todoListService.delete(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
