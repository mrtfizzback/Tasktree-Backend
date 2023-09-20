package com.josealmeida.testeJpa2.controller;

import com.josealmeida.testeJpa2.DTO.UserDTO;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
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

    @PutMapping("/{id}")
    public UserDTO updateUser(@RequestBody User updatedUser, @PathVariable Long id){
        userService.updateUser(updatedUser, id);
        return UserDTO.userToDTO(updatedUser);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User with id "+ id +" was deleted";
    }

}
