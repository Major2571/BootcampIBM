package com.todo.todobackend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/to-do")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    /**
     * Retrieve a specific ToDo item by its ID.
     *
     * @param id The ID of the ToDo item to retrieve.
     * @return ResponseEntity containing the retrieved ToDo item.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ToDo> findById(@PathVariable Integer id) {
        ToDo obj = toDoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    /**
     * Retrieve all open ToDo items.
     *
     * @return ResponseEntity containing a list of open ToDo items.
     */
    @GetMapping("/open")
    public ResponseEntity<List<ToDo>> findOpen() {
        List<ToDo> list = toDoService.findAllOpen();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Retrieve all closed ToDo items.
     *
     * @return ResponseEntity containing a list of closed ToDo items.
     */
    @GetMapping("/closed")
    public ResponseEntity<List<ToDo>> findClosed() {
        List<ToDo> list = toDoService.findAllClosed();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Retrieve all ToDo items.
     *
     * @return ResponseEntity containing a list of all ToDo items.
     */
    @GetMapping("/all")
    public ResponseEntity<List<ToDo>> findAll() {
        List<ToDo> list = toDoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Create a new ToDo item.
     *
     * @param obj The ToDo item to create.
     * @return ResponseEntity with the newly created ToDo item's URI.
     */
    @PostMapping
    public ResponseEntity<ToDo> create(@RequestBody ToDo obj) {
        obj = toDoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    /**
     * Delete a ToDo item by its ID.
     *
     * @param id The ID of the ToDo item to delete.
     * @return ResponseEntity with a no content status.
     */
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        toDoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Update a ToDo item by its ID.
     *
     * @param id  The ID of the ToDo item to update.
     * @param obj The updated ToDo item.
     * @return ResponseEntity containing the updated ToDo item.
     */
    @PutMapping("/{id}/update")
    public ResponseEntity<ToDo> update(@PathVariable Integer id, @RequestBody ToDo obj) {
        ToDo attObj = toDoService.update(id, obj);
        return ResponseEntity.ok().body(attObj);
    }
}
