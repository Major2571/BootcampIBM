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

    @Test
    public void testCreate() throws Exception {
        // Cenário
        String dateSrt = "20/08/2023";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ToDo mockToDo = new ToDo(1, "Something", "Someone", sdf.parse(dateSrt), false);
        when(toDoService.create(any(ToDo.class))).thenReturn(mockToDo);

        // Execução e Verificação
        mockMvc.perform(MockMvcRequestBuilders.post("/to-do")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"id\":1,\"title\":\"Something\",\"description\":\"Someone\",\"dateEnd\":\"20/08/2023\",\"completed\":\"false\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/to-do/1"));
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/to-do/1/delete")).andExpect(status().isNoContent());
    }

}
