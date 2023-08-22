package com.todo.todobackend.service;

import java.util.List;
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
    
    public List<ToDo> findAllOpen() {
        List<ToDo> list = toDoRepository.findAllOpen();
        return list;
    }

    public List<ToDo> findAllClosed() {
        List<ToDo> listaFechada = toDoRepository.findAllClosed();
        return listaFechada;
    }

    public List<ToDo> findAll() {
        List<ToDo> list = toDoRepository.findAll();
        return list;
    }

    public ToDo create(ToDo obj) {
        obj.setId(null);
        return toDoRepository.save(obj);
    }
    
    public void delete(Integer id) {
        toDoRepository.deleteById(id);
    }
}
