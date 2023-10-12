package com.josealmeida.testeJpa2.controller;

import com.josealmeida.testeJpa2.DTO.LoginsResponseDTO;
import com.josealmeida.testeJpa2.infra.security.TokenService;
import com.josealmeida.testeJpa2.model.DTO.AuthenticationDTO;
import com.josealmeida.testeJpa2.model.DTO.RegisteredDTO;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

     @Autowired
     private UserRepository userRepository;

     @Autowired
     private TokenService tokenService;

     @GetMapping("/finbylogin")
     User findByLogin(@RequestBody String data){
         return userRepository.findByLogin(data);
     }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginsResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisteredDTO data){
        if(this.userRepository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();

    }
}
