package com.todo.todobackend.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todobackend.domain.ToDo;
import com.todo.todobackend.repository.ToDoRepository;

@Service
public class DBService {
    
    @Autowired
    private ToDoRepository toDoRepository;

    public void instanciaBancoDeDados() throws ParseException{

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ToDo t1 = new ToDo(null, "Terminar projeto SpringBoot", "Terminar ToDoList do Bootcamp", sdf.parse("20/08/2023"), false);
        ToDo t2 = new ToDo(null, "Terminar Livro", "Terminar de ler Jantar Secreto", sdf.parse("30/08/2023"), false);

        toDoRepository.saveAll(Arrays.asList(t1, t2));

    }
}
