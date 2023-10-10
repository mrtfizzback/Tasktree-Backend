package com.josealmeida.testeJpa2.controller;

import com.josealmeida.testeJpa2.DTO.TaskDTO;
import com.josealmeida.testeJpa2.model.Task;
import com.josealmeida.testeJpa2.service.TaskService;
import com.josealmeida.testeJpa2.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/task")
public class TaskController {


    private final UserService userService;

    private final TaskService taskService;

    public TaskController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
//    public String teste(){
//        return "testeee";
//    }
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping("/newtask/{managerid}")
    public String newTask(@RequestBody Task newTask, @PathVariable Long managerid){
        return taskService.saveTask(newTask, managerid);
    }

    @PutMapping("/{id}")
    public Task updateTask(@RequestBody Task updatedTask, @PathVariable Long id){
        return taskService.updateTask(updatedTask, id);
    }
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "Task with id " + id + " was deleted";
    }

    @PutMapping("/assignparent/{taskid}/{newparentid}")
    public String assignParentTask(@PathVariable Long taskid, @PathVariable Long newparentid) {
        taskService.assignParentTask(taskid, newparentid);
        Task task = taskService.getTaskById(taskid);
        return newparentid == 0 ? "SPRINGBOOT: Parent of " + task.getTitle() + " is null" : "SPRINGBOOT: "+ task.getParentTask().getTitle() + ", is the parent of " + task.getTitle();
    }

    @PutMapping("/assigntaskmanager/{taskid}/{userid}")
    public String assignTaskManager(@PathVariable Long taskid, @PathVariable Long userid){
        String response = taskService.assignTaskManager(taskid, userid);
        return response;
    }
}
