package com.josealmeida.testeJpa2.controller;

import com.josealmeida.testeJpa2.DTO.TaskDTO;
import com.josealmeida.testeJpa2.model.Task;
import com.josealmeida.testeJpa2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/task/{id}")
    public TaskDTO getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping("/task")
    public Task newTask(@RequestBody Task newTask){
        return taskService.saveTask(newTask);
    }

    @PutMapping("/task/{id}")
    public Task updateTask(@RequestBody Task updatedTask, @PathVariable Long id){
        return taskService.updateTask(updatedTask, id);
    }
    @DeleteMapping("/task/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "Task with id " + id + " was deleted";
    }

}
