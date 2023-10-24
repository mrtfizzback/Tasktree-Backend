package com.josealmeida.testeJpa2.controller;


import com.josealmeida.testeJpa2.DTO.UserAuthResponseDTO;
import com.josealmeida.testeJpa2.model.AuthRequest;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.service.JwtService;
import com.josealmeida.testeJpa2.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody User userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public UserAuthResponseDTO authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        System.out.println("This is the authentication: "+ authentication);
        if (authentication.isAuthenticated()) {
            User user = userInfoService.getUserByUserName(authRequest.getUserName());
            String token = jwtService.generateToken(authRequest.getUserName());
            UserAuthResponseDTO userAuthResponseDTO = new UserAuthResponseDTO(user.getUserName(),user.getEmail(), user.getRoles(), token);
            return userAuthResponseDTO;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @GetMapping("/allusers")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    public List<User> getAllUsers (){
        return userInfoService.getAllUsers();
    }

}
