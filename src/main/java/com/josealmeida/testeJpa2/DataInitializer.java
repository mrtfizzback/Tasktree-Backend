package com.josealmeida.testeJpa2;

import com.josealmeida.testeJpa2.controller.UserAuthController;
import com.josealmeida.testeJpa2.model.Task;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.repository.TaskRepository;
import com.josealmeida.testeJpa2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthController userAuthController;

    @Override
    public void run(String... args) throws Exception {

        taskRepository.deleteAll();
        userRepository.deleteAll();
        // Create Tasks
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setTaskDescription("Task 1 Desccription");
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setTaskDescription("Task 2 Desccription");
        task2.setParentTask(task1);
        taskRepository.save(task2);

        Task task3 = new Task();
        task3.setTitle("Task 3");
        task3.setTaskDescription("Task 3 Desccription");
        task3.setParentTask(task2);
        taskRepository.save(task3);

        Task task4 = new Task();
        task4.setTitle("Task 4");
        task4.setTaskDescription("Task 4 Desccription");
        taskRepository.save(task4);

        // Create Users
        User user1 = new User();
        user1.setUserName("user1");
        user1.setPassword("12345");
        user1.setEmail("user1@example.com");
        user1.setRoles("ROLE_USER");

        User user2 = new User();
        user2.setUserName("admin");
        user2.setPassword("12345");
        user2.setEmail("user2@example.com");
        user2.setRoles("ROLE_ADMIN");

        userAuthController.addNewUser(user2);
        userAuthController.addNewUser(user1);

    }
}
