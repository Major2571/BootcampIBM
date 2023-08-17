package com.todo.todobackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todobackend.domain.ToDo;
import com.todo.todobackend.service.ToDoService;

@RestController
@RequestMapping("/to-do")
public class ToDoController {
    
    @Autowired
    private ToDoService toDoService;

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> findById(@PathVariable Integer id){
        ToDo obj = toDoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
