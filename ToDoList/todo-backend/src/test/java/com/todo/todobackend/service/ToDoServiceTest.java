package com.todo.todobackend.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.todo.todobackend.domain.ToDo;
import com.todo.todobackend.repository.ToDoRepository;
import com.todo.todobackend.service.exceptions.ObjectNotFoundException;

@SpringBootTest
public class ToDoServiceTest {
    
    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    @Test
    public void testFindById(){

        Integer id = 1;

        ToDo mockToDo = new ToDo();
        mockToDo.setId(id);

        when(toDoRepository.findById(id)).thenReturn(Optional.of(mockToDo));

        ToDo result = toDoService.findById(id);

        assertEquals(mockToDo, result);

        verify(toDoRepository, times(1)).findById(id);

    }

    @Test
    public void testFindAllOpen() {
        // Cenário
        List<ToDo> mockList = new ArrayList<>();
        mockList.add(new ToDo());
        when(toDoRepository.findAllOpen()).thenReturn(mockList);

        // Execução
        List<ToDo> result = toDoService.findAllOpen();

        // Verificação
        assertEquals(1, result.size());
    }

    // Testes para outros métodos seguiriam o mesmo padrão

    @Test
    public void testFindByIdNotFound() {
        // Cenário
        when(toDoRepository.findById(1)).thenReturn(Optional.empty());

        // Execução e Verificação
        assertThrows(ObjectNotFoundException.class, () -> toDoService.findById(1));
    }

    @Test
    public void testCreate() {
        // Cenário
        ToDo mockToDo = new ToDo();
        when(toDoRepository.save(any(ToDo.class))).thenReturn(mockToDo);

        // Execução
        ToDo result = toDoService.create(new ToDo());

        // Verificação
        assertNotNull(result);
    }

    @Test
    public void testDelete() {
        assertDoesNotThrow(() -> toDoService.delete(1));
        verify(toDoRepository, times(1)).deleteById(1);
    }

    @Test
    public void testUpdate() {
        // Cenário
        ToDo existingToDo = new ToDo();
        existingToDo.setId(1);
        when(toDoRepository.findById(1)).thenReturn(Optional.of(existingToDo));
        when(toDoRepository.save(any(ToDo.class))).thenReturn(existingToDo);

        // Execução
        ToDo updatedToDo = new ToDo();
        updatedToDo.setTitle("Updated ToDo");
        ToDo result = toDoService.update(1, updatedToDo);

        // Verificação
        assertNotNull(result);
        assertEquals("Updated ToDo", result.getTitle());
    }
}
