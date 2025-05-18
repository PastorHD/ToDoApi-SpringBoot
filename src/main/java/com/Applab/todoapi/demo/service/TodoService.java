package com.Applab.todoapi.demo.service;

import com.Applab.todoapi.demo.entity.ToDo;
import com.Applab.todoapi.demo.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private ToDoRepository toDoRepository;

    public void ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public TodoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDo crearToDo(ToDo todo) {
        return toDoRepository.save(todo);
    }

    public List<ToDo> obtenerTodos() {
        return toDoRepository.findAll();
    }

    public Optional<ToDo> actualizarToDo(Long id, ToDo todoActualizado) {
        return toDoRepository.findById(id).map(todo -> {
            todo.setDescripcion(todoActualizado.getDescripcion());
            todo.setFecha(todoActualizado.getFecha());
            todo.setEstatus(todoActualizado.getEstatus());
            return toDoRepository.save(todo);
        });
    }

    public Optional<ToDo> actualizarEstatus(Long id, String estatus) {
        return toDoRepository.findById(id).map(todo -> {
            todo.setEstatus(estatus);
            return toDoRepository.save(todo);
        });
    }

    public boolean eliminarToDo(Long id) {
        if (toDoRepository.existsById(id)) {
            toDoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
