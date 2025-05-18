package com.Applab.todoapi.demo.controller;

import com.Applab.todoapi.demo.entity.ToDo;
import com.Applab.todoapi.demo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    private final TodoService toDoService;

    public ToDoController(TodoService toDoService) {
        this.toDoService = toDoService;
    }

    // 1. Crear un nuevo ToDo (POST)
    @PostMapping
    public ResponseEntity<ToDo> crearToDo(@Valid @RequestBody ToDo todo) {
        ToDo creado = toDoService.crearToDo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // 2. Obtener todos los ToDos (GET)
    @GetMapping
    public ResponseEntity<List<ToDo>> obtenerTodos() {
        List<ToDo> todos = toDoService.obtenerTodos();
        return ResponseEntity.ok(todos);
    }

    // 3. Actualizar toda la info de un ToDo (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<ToDo> actualizarToDo(@PathVariable Long id, @Valid @RequestBody ToDo todo) {
        return toDoService.actualizarToDo(id, todo)
                .map(updated -> ResponseEntity.ok(updated))
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Cambiar solo estatus (PATCH)
    @PatchMapping("/{id}/estatus")
    public ResponseEntity<ToDo> actualizarEstatus(@PathVariable Long id, @RequestBody String estatus) {
        return toDoService.actualizarEstatus(id, estatus)
                .map(updated -> ResponseEntity.ok(updated))
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar ToDo por ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarToDo(@PathVariable Long id) {
        boolean eliminado = toDoService.eliminarToDo(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
