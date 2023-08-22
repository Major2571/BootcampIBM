package com.todo.todobackend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @PostMapping
    public ResponseEntity<ToDo> create(@RequestBody ToDo obj){
        obj = toDoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        toDoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> update(@PathVariable Integer id, @RequestBody ToDo obj){
        ToDo attObj = toDoService.update(id, obj);
        return ResponseEntity.ok().body(attObj);
    }
}
