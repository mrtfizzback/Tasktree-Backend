package com.josealmeida.testeJpa2;

import com.josealmeida.testeJpa2.model.Task;
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

//    @Override
//    public void run(String... args) throws Exception {
//
//        // Create Users
//        User user1 = new User();
//        user1.setUsername("user1");
//        user1.setName("User 1");
//        user1.setEmail("user1@example.com");
//        user1.setUserType(UserType.FREE_USER); // Or UserType.PREMIUM
//        user1.setPhotoProfile(new byte[0]); // Add profile photo if needed
//
//        User user2 = new User();
//        user2.setUsername("user2");
//        user2.setName("User 2");
//        user2.setEmail("user2@example.com");
//        user2.setUserType(UserType.PREMIUM); // Or UserType.PREMIUM
//        user2.setPhotoProfile(new byte[0]); // Add profile photo if needed
//
////        // Create and add managing tasks to user1
////        Set<Task> managingTasks1 = new HashSet<>();
////        managingTasks1.add(task1);
////        managingTasks1.add(task2);
////        user1.setManagingTasks(managingTasks1);
//
//
//
//
//
//        // Create Tasks
//        Task task1 = new Task();
//        task1.setTitle("Task 1");
//        task1.setTaskDescription("Task 1 Desccription");
//        task1.setTaskManager(user1);
//
//        // Set other task properties...
//
//
//        Task task2 = new Task();
//        task2.setTitle("Task 2");
//        task2.setTaskDescription("Task 2 Desccription");
//        task2.setParentTask(task1);
//        task2.setTaskManager(user1);
//
//
//
//
//        Task task3 = new Task();
//        task3.setTitle("Task 3");
//        task3.setTaskDescription("Task 3 Desccription");
//        task3.setParentTask(task2);
//        task3.setTaskManager(user1);
//
//        // Set other task properties...
//
//
//        Task task4 = new Task();
//        task4.setTitle("Task 4");
//        task4.setTaskDescription("Task 4 Desccription");
//        task4.setTaskManager(user2);
//
//        // Set other task properties...
//
//
//
//
//        user1.setPartOfTeamTasks(new HashSet<>()); // Add participating tasks if needed
//        userRepository.save(user1);
//        userRepository.save(user2);
//        taskRepository.save(task1);
//        taskRepository.save(task2);
//        taskRepository.save(task3);
//        taskRepository.save(task4);
//    }

    @Override
    public void run(String... args) throws Exception {

        taskRepository.deleteAll();
        userRepository.deleteAll();
        // Create Tasks
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setTaskDescription("Task 1 Desccription");

        // Set other task properties...
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setTaskDescription("Task 2 Desccription");
        task2.setParentTask(task1);
        // Set other task properties...
        taskRepository.save(task2);

        Task task3 = new Task();
        task3.setTitle("Task 3");
        task3.setTaskDescription("Task 3 Desccription");
        task3.setParentTask(task2);
        // Set other task properties...
        taskRepository.save(task3);

        Task task4 = new Task();
        task4.setTitle("Task 4");
        task4.setTaskDescription("Task 4 Desccription");
        // Set other task properties...
        taskRepository.save(task4);
//
//        // Create Users
//        User user1 = new User();
//        user1.setUsername("user1");
//        user1.setName("User 1");
//        user1.setEmail("user1@example.com");
//        user1.setUserType(UserType.FREE_USER); // Or UserType.PREMIUM
//        user1.setPhotoProfile(new byte[0]); // Add profile photo if needed
//
//        User user2 = new User();
//        user2.setUsername("user2");
//        user2.setName("User 2");
//        user2.setEmail("user2@example.com");
//        user2.setUserType(UserType.PREMIUM); // Or UserType.PREMIUM
//        user2.setPhotoProfile(new byte[0]); // Add profile photo if needed
//
//        // Create and add managing tasks to user1
//        Set<Task> managingTasks1 = new HashSet<>();
//        managingTasks1.add(task1);
//        managingTasks1.add(task2);
//        user1.setManagingTasks(managingTasks1);
//
//        user1.setPartOfTeamTasks(new HashSet<>()); // Add participating tasks if needed
//        userRepository.save(user1);
//        userRepository.save(user2);


    }
}
