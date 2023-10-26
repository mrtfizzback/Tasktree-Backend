package com.josealmeida.testeJpa2.controller;

import com.josealmeida.testeJpa2.DTO.TaskDTO;
import com.josealmeida.testeJpa2.DTO.UserDTO;
import com.josealmeida.testeJpa2.model.Task;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.service.TaskService;
import com.josealmeida.testeJpa2.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;




    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/newUser")
    public UserDTO newUser(@RequestBody User newUser){
        userService.saveUser(newUser);
        return UserDTO.userToDTO(newUser);
    }

//    @PutMapping("/{id}")
//    public UserDTO updateUser(@RequestBody User updatedUser, @PathVariable Long id){
//        userService.updateUser(updatedUser, id);
//        return UserDTO.userToDTO(updatedUser);
//    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User with id "+ id +" was deleted";
    }

    //    @PutMapping("/assignmanager/{taskid}/{userid}")
//    public String assignManager(@PathVariable Long taskId, @PathVariable Long userId, User newManager){
//        taskService.getTaskById(taskId).setTaskManager(UserDTO.dtoToUser(userService.getUserById(userId)));
//
//
//        return ""
//    }
    @PutMapping("/assignmanager/{taskid}/{userid}")
    public String assignmanager(@PathVariable Long taskid, @PathVariable Long userid){
        TaskDTO taskDTO = userService.assignNewManager(taskid, userid);
        UserDTO userDTO = userService.getUserById(userid);

        return "User " + userDTO.getName() + " is the Task Manager of: " + taskDTO.getTitle();
    }

    @PutMapping("/addtoteam/{taskid}/{userid}")
    public String addtoteam(@PathVariable Long taskid, @PathVariable Long userid){
        TaskDTO taskDTO = userService.addUsertoTeam(taskid, userid);
        UserDTO userDTO = userService.getUserById(userid);

        return "User " + userDTO.getName() + " was added to team of the task: " + taskDTO.getTitle();
    }



}
