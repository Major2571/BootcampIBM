package com.todo.todobackend.controller;

import java.util.List;

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

    @GetMapping("/open")
    public ResponseEntity<List<ToDo>> findOpen(){
        List<ToDo> list = toDoService.findAllOpen();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/closed")
    public ResponseEntity<List<ToDo>> findClosed(){
        List<ToDo> list = toDoService.findAllClosed();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ToDo>> findAll(){
        List<ToDo> list = toDoService.findAll();
        return ResponseEntity.ok().body(list);
    }

}
