package com.josealmeida.testeJpa2;

import com.josealmeida.testeJpa2.model.Task;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.model.enums.TaskType;
import com.josealmeida.testeJpa2.model.enums.UserType;
import com.josealmeida.testeJpa2.repository.TaskRepository;
import com.josealmeida.testeJpa2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private TaskRepository taskRepository; // Replace with your repository
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create Tasks
        Task task1 = new Task();
        task1.setTitle("Task 1");
        // Set other task properties...
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("Task 2");
        // Set other task properties...
        taskRepository.save(task2);

        // Create Users
        User user1 = new User();
        user1.setUsername("user1");
        user1.setName("User One");
        user1.setEmail("user1@example.com");
        user1.setUserType(UserType.FREE_USER); // Or UserType.PREMIUM
        user1.setPhotoProfile(new byte[0]); // Add profile photo if needed

        // Create and add managing tasks to user1
        Set<Task> managingTasks1 = new HashSet<>();
        managingTasks1.add(task1);
        managingTasks1.add(task2);
        user1.setManagingTasks(managingTasks1);

        user1.setParticipatingTasks(new HashSet<>()); // Add participating tasks if needed
        userRepository.save(user1);
    }
}

