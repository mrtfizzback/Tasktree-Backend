package com.josealmeida.testeJpa2.controller;

import com.josealmeida.testeJpa2.DTO.NewTaskDTO;
import com.josealmeida.testeJpa2.DTO.TaskDTO;
import com.josealmeida.testeJpa2.model.AuthRequest;
import com.josealmeida.testeJpa2.model.Task;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.service.JwtService;
import com.josealmeida.testeJpa2.service.TaskService;
import com.josealmeida.testeJpa2.service.UserInfoService;
import com.josealmeida.testeJpa2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("http://localhost:5173/home")
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


//    @Autowired
//    private final UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private final TaskService taskService;

    public TaskController(UserInfoService userInfoService, TaskService taskService) {
        this.userInfoService = userInfoService;
        this.taskService = taskService;
    }
    @GetMapping("/tasks")

//    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
//    public String teste(){
//        return "testeee";
//    }
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }


    @PostMapping("/newtask")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String newTask(@RequestBody NewTaskDTO newTask){
        return taskService.saveTask(newTask);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public Task updateTask(@RequestBody Task updatedTask, @PathVariable Long id){
        return taskService.updateTask(updatedTask, id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "Task with id " + id + " was deleted";
    }
    @PutMapping("/assignparent/{taskid}/{newparentid}")
  //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String assignParentTask(@PathVariable Long taskid, @PathVariable Long newparentid) {
        taskService.assignParentTask(taskid, newparentid);
        Task task = taskService.getTaskById(taskid);
        return newparentid == 0 ? "SPRINGBOOT: Parent of " + task.getTitle() + " is null" : "SPRINGBOOT: "+ task.getParentTask().getTitle() + ", is the parent of " + task.getTitle();
    }

//    @PutMapping("/assigntaskmanager/{taskid}/{userid}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String assignTaskManager(@PathVariable Long taskid, @PathVariable Long userid){
//        String response = taskService.assignTaskManager(taskid, userid);
//        return response;
//    }

//    @PostMapping("/generateToken")
//    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(authRequest.getUserName());
//        } else {
//            throw new UsernameNotFoundException("invalid user request !");
//        }
//    }
}
