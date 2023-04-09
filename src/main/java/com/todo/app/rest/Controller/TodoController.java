package com.todo.app.rest.Controller;


import com.todo.app.rest.Model.Task;
import com.todo.app.rest.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(value ="/")
    public String holaMundo(){
        return "HOLA MUNDO!";
    }

    @GetMapping(value = "/tasks")
    public List<Task> getTasks() {
        return todoRepository.findAll();
    }

    @PostMapping(value = "/savetask")
    public String saveTask(@RequestBody Task task){
        todoRepository.save(task);
        return "Saved task";
    }

    @PutMapping(value = "/update/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody Task task){
        Task updateTask = todoRepository.findById(id).get();
        updateTask.setTitulo(task.getTitulo());
        updateTask.setDescripcion(task.getDescripcion());
        todoRepository.save(updateTask);
        return "Updated Task";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        Task deleteTask = todoRepository.findById(id).get();
        todoRepository.delete(deleteTask);
        return "Deleted Task";
    }

}
