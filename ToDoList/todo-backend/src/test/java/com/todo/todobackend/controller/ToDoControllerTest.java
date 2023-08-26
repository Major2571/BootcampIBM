package com.todo.todobackend.controller;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
@WebMvcTest(ToDoController.class)
public class ToDoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @InjectMocks
    private ToDoControllerTest toDoController;

}
