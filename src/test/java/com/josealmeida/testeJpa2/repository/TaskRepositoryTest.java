package com.josealmeida.testeJpa2.repository;


import com.josealmeida.testeJpa2.model.Task;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EntityManager entityManager;

/*    @BeforeEach
    void setUp() {
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task("task 1", "task1 description");
        Task task2 = new Task("task 2", "task2 description");
        tasks.add(task1);
        tasks.add(task2);
        taskRepository.saveAll(tasks);
    }*/

    @Test
    @DisplayName("should create a new task")
    void testSaveTask() {
        Task task = new Task();
        task.setTitle("task 1");
        task.setTaskDescription("task 1 description");

        taskRepository.save(task);

        Task retrievedTask = taskRepository.findById(task.getId()).orElse(null);

        assertThat(retrievedTask).isNotNull();
        assertThat(retrievedTask.getTitle()).isEqualTo("task 1");
    }

    @Test
    @DisplayName("should find all tasks")
    void testFindAllTasks() {
        // Create and save sample tasks
        Task task = new Task();
        task.setTitle("Task 1");
        task.setTaskDescription("task 1 description");


        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setTaskDescription("task 2 description");

        taskRepository.saveAll(List.of(task, task2));

        List<Task> tasks = taskRepository.findAll();

        assertThat(tasks.size()).isEqualTo(2);
        assertThat(tasks).extracting(Task::getTitle).contains("Task 1", "Task 2");
    }

    @Test
    @DisplayName("should find task by ID")
    void testFindById() {
        //Arrange
        Task task = new Task("Task for ID", "Description for ID");
        task = taskRepository.save(task);

        //Act
        Optional<Task> foundTask = taskRepository.findById(task.getId());

        // Assert - The retrieved task should match the saved task
        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getTitle()).isEqualTo("Task for ID");
    }

    @Test
    @DisplayName("should update an existing task")
    void testUpdateTask() {
        // Arrange: Create, save, and then update a task
        Task task = new Task("Initial Title", "Initial Description");
        task = taskRepository.save(task);

        task.setTitle("Updated Title");
        task.setTaskDescription("Updated Description");
        taskRepository.save(task);

        // Act - retirve the updated task
        Task updatedTask = taskRepository.findById(task.getId()).orElse(null);

        // Assert the values are updated
        assertThat(updatedTask).isNotNull();
        assertThat(updatedTask.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedTask.getTaskDescription()).isEqualTo("Updated Description");
    }

    @Test
    @DisplayName("should delete a task")
    void testDeleteTask() {
        // Arrange
        Task task = new Task("Task to Delete", "Description to Delete");
        task = taskRepository.save(task);

        // Act - Delete the task
        taskRepository.delete(task);
        Optional<Task> deletedTask = taskRepository.findById(task.getId());

        // Assert- The task optional should be empty
        assertThat(deletedTask).isEmpty();
    }

}