package com.todo.todobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.todobackend.domain.ToDo;

@Repository
public interface ToDoRepository  extends JpaRepository<ToDo, Integer> {
    
}
