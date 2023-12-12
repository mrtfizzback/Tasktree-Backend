package com.josealmeida.testeJpa2.service;

import com.josealmeida.testeJpa2.DTO.NewTaskDTO;
import com.josealmeida.testeJpa2.enums.TaskType;
import com.josealmeida.testeJpa2.enums.UserRole;
import com.josealmeida.testeJpa2.model.Task;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.repository.TaskRepository;
import com.josealmeida.testeJpa2.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

//    @Autowired
    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should retrieve all tasks")
    void shouldGetAllTasks() {
        // Arrange
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setTaskDescription("task 1 description");

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setTaskDescription("task 2 description");

        //List<Task> mockTasks = List.of(task1, task2);
        List<Task> mockTasks = new ArrayList<>();
        mockTasks.add(task1);
        mockTasks.add(task2);
        when(taskRepository.findAll()).thenReturn(mockTasks);

        // Act
        List<Task> tasks = taskService.getAllTasks();

        // Assert
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());

    }


  @Test
  @DisplayName("Should retrieve a task by ID")
  void testGetTaskById() {
      // Arrange
      Long taskId = 1L;
      Task task = new Task();
      task.setId(taskId);
      task.setTitle("Task 1");
      when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

      // Act
      Optional<Task> result = Optional.ofNullable(taskService.getTaskById(taskId));

      // Assert
      assertTrue(result.isPresent());
      assertEquals(taskId, result.get().getId());
      assertEquals("Task 1", result.get().getTitle());
  }



    @Test
    @DisplayName("Should save a task correctly")
    void saveTask() {
        // Arrange
        String managerName = "Admin_1";
        Long managerId = 1L;
        NewTaskDTO newTaskDTO = new NewTaskDTO(
                "task 1",
                "task 1 Description",
                TaskType.TASK,
                managerName
        );

        User manager = new User();
        manager.setId(managerId);
        manager.setUserName(managerName);

        when(userRepository.findByUserName(managerName)).thenReturn(Optional.of(manager));


        // Act
        String response = taskService.saveTask(newTaskDTO);

        // Assert
        assertNotNull(response);
        assertTrue(response.contains("Task created successfully"));
        verify(taskRepository).save(any(Task.class));
    }




    @Test
    @DisplayName("Should update a task correctly")
    void updateTask() {
        // Arrange
        Long taskId = 1L;
        Long userId = 1L;
        User user = new User("Admin_3", "12345", "ROLE_USER");

        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTitle("Old Title");
        existingTask.setTaskDescription("Exsiting task description");
        existingTask.setTaskType(TaskType.TASK);
        existingTask.setTaskManager(user);

        Task updatedTask = new Task();
        updatedTask.setTitle("New Title");
        updatedTask.setTaskDescription("New task description");
        updatedTask.setTaskType(TaskType.TASK);
        updatedTask.setTaskManager(user);
        updatedTask.setId(existingTask.getId());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(updatedTask);
        // Act

        Task result = taskService.updateTask(updatedTask, taskId);

        // Assert
        assertNotNull(result);
        assertEquals(taskId, result.getId());
        assertEquals("New Title", result.getTitle());
        verify(taskRepository).findById(taskId);
        verify(taskRepository).save(existingTask);
    }

/*
    @Test
    void deleteTask() {
    }

    @Test
    void assignParentTask() {
    }

    @Test
    void assignTaskManager() {
    }*/


}