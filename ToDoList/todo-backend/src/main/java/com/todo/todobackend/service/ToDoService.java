package com.todo.todobackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.todo.todobackend.domain.ToDo;
import com.todo.todobackend.repository.ToDoRepository;
import org.springframework.stereotype.Service;
import com.todo.todobackend.service.exceptions.ObjectNotFoundException;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    /**
     * Find a ToDo item by its ID.
     *
     * @param id The ID of the ToDo item to find.
     * @return The retrieved ToDo item.
     * @throws ObjectNotFoundException if the ToDo item with the given ID is not
     *                                 found.
     */
    public ToDo findById(Integer id) {
        Optional<ToDo> obj = toDoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado: " + id + ", Tipo: " + ToDo.class.getName()));
    }

    /**
     * Find all open ToDo items.
     *
     * @return A list of open ToDo items.
     */
    public List<ToDo> findAllOpen() {
        List<ToDo> list = toDoRepository.findAllOpen();
        return list;
    }

    /**
     * Find all closed ToDo items.
     *
     * @return A list of closed ToDo items.
     */
    public List<ToDo> findAllClosed() {
        List<ToDo> listaFechada = toDoRepository.findAllClosed();
        return listaFechada;
    }

    /**
     * Find all ToDo items.
     *
     * @return A list of all ToDo items.
     */
    public List<ToDo> findAll() {
        List<ToDo> list = toDoRepository.findAll();
        return list;
    }

    /**
     * Create a new ToDo item.
     *
     * @param obj The ToDo item to create.
     * @return The newly created ToDo item.
     */
    public ToDo create(ToDo obj) {
        obj.setId(null);
        obj.setCompleted(false);
        return toDoRepository.save(obj);
    }

    /**
     * Delete a ToDo item by its ID.
     *
     * @param id The ID of the ToDo item to delete.
     */
    public void delete(Integer id) {
        toDoRepository.deleteById(id);
    }

    /**
     * Update a ToDo item by its ID.
     *
     * @param id  The ID of the ToDo item to update.
     * @param obj The updated ToDo item.
     * @return The updated ToDo item.
     */
    public ToDo update(Integer id, ToDo obj) {
        ToDo attObj = findById(id);
        attObj.setTitle(obj.getTitle());
        attObj.setDescription(obj.getDescription());
        attObj.setDateEnd(obj.getDateEnd());
        attObj.setCompleted(obj.getCompleted());
        return toDoRepository.save(attObj);
    }

}
