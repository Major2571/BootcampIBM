package com.todo.todobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.todo.todobackend.domain.ToDo;

@Repository
public interface ToDoRepository  extends JpaRepository<ToDo, Integer> {
    
    @Query("SELECT obj FROM ToDo obj WHERE obj.completed = false ORDER BY obj.dateEnd")
    List<ToDo> findAllOpen();

    @Query("SELECT obj FROM ToDo obj WHERE obj.completed = true ORDER BY obj.dateEnd")
    List<ToDo> findAllClosed();

}
