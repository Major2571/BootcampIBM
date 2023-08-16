package com.todo.todobackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todobackend.service.ToDoService;

@RestController
@RequestMapping("/to-do")
public class ToDoController {
    
    @Autowired
    private ToDoService toDoService;


}
