package com.todo.todobackend.service;
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

}
