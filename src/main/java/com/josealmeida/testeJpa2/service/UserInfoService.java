package com.josealmeida.testeJpa2.service;

import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = repository.findByUserName(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(User userInfo) {
        try {
            Optional<User> existingUser = repository.findByUserName(userInfo.getUserName());
            if (existingUser.isPresent()) {
                throw new IllegalArgumentException("User with username " + userInfo.getUserName() + " already exists.");
            }
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            repository.save(userInfo);
            return "User Added Successfully";
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while saving user: " + e.getMessage());
        }
    }

    public User getUserByUserName(String userName) {
        return repository.findByUserName(userName)
                .orElseThrow(() -> new NoSuchElementException("User with username " + userName + " not found."));
    }

    public List<User> getAllUsers() {
        try {
            return repository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while fetching users: " + e.getMessage());
        }
    }


}