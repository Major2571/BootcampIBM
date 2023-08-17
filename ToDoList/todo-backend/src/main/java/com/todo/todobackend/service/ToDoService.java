package com.todo.todobackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.todo.todobackend.domain.ToDo;
import com.todo.todobackend.repository.ToDoRepository;
import com.todo.todobackend.service.exceptions.ObjectNotFoundException;

public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public ToDo findById(Integer id) {
        Optional<ToDo> obj = toDoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado: " + id + ", Tipo: "+  ToDo.class.getName()));
    }
    
}
