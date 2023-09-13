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

    /**
     * Populate the database with initial ToDo items.
     *
     * @throws ParseException if there is an error parsing dates.
     */
    public void instanciaBancoDeDados() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ToDo t1 = new ToDo(null, "Supermercado", "Comprar: arroz, feijão, macarrão", sdf.parse("2023-08-06"), false);
        ToDo t2 = new ToDo(null, "Ler um Livro", "Ler um capítulo do livro O Hobbit", sdf.parse("2023-08-07"), false);
        ToDo t3 = new ToDo(null, "Terminar projeto JS", "Terminar projeto de JS", sdf.parse("2023-08-13"), false);
        ToDo t4 = new ToDo(null, "Desafio IBM", "Fazer desafio final do Bootcamp IBM para próxima fase", sdf.parse("2023-08-13"), false);

        toDoRepository.saveAll(Arrays.asList(t1, t2, t3, t4));

    }
}
