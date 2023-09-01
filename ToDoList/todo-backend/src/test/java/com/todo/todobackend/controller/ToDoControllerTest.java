package com.todo.todobackend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.todo.todobackend.domain.ToDo;
import com.todo.todobackend.service.ToDoService;

@WebMvcTest(ToDoController.class)
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @InjectMocks
    private ToDoControllerTest toDoController;

    @Test
    public void testCreate() throws Exception {
        // Cenário
        String dateSrt = "2023-09-03";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ToDo mockToDo = new ToDo(1, "Something", "Someone", sdf.parse(dateSrt), false);
        when(toDoService.create(any(ToDo.class))).thenReturn(mockToDo);

        // Execução e Verificação
        mockMvc.perform(MockMvcRequestBuilders.post("/to-do")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"id\":1,\"title\":\"Something\",\"description\":\"Someone\",\"dateEnd\":\"2023-09-03\",\"completed\":\"false\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/to-do/1"));
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/to-do/1/delete")).andExpect(status().isNoContent());
    }

}
