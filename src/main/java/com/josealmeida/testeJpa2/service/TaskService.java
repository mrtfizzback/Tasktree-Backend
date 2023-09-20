package com.josealmeida.testeJpa2.service;

import com.josealmeida.testeJpa2.DTO.TaskDTO;
import com.josealmeida.testeJpa2.model.Task;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public TaskDTO getTaskById(Long id){
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            return TaskDTO.taskToDTO(task);
        }

        return null;
    }

    public Task saveTask(Task newTask){
        return taskRepository.save(newTask);
    }

    public Task updateTask(Task newTask, Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        Task oldtask = null;
        if (taskOptional.isPresent()) {
            oldtask = taskOptional.get();
        }
        Task updatedTask = new Task();
        oldtask.setTitle(newTask.getTitle());
        oldtask.setTaskDescription(newTask.getTaskDescription());
        oldtask.setTasktype(newTask.getTasktype());
        oldtask.setTaskCreator(newTask.getTaskCreator());
        oldtask.setTaskManager(newTask.getTaskManager());
        oldtask.setTaskTeam(newTask.getTaskTeam());
        oldtask.setParentTask(newTask.getParentTask());
        oldtask.setCompleted(newTask.isCompleted());
        oldtask.setFinanced(newTask.isFinanced());
        oldtask.setTeamComplete(newTask.isTeamComplete());
        oldtask.setGovernmentApproved(newTask.isGovernmentApproved());
        return oldtask;
    }

    public void deleteTask(Long id){
        Optional<Task> taskOptional = taskRepository.findById(id);
        Task oldtask = null;
        if (taskOptional.isPresent()) {
            oldtask = taskOptional.get();
        }

        oldtask.setTaskCreator(null);
        oldtask.setTaskManager(null);
        oldtask.getTaskTeam().clear();

        User taskManager = oldtask.getTaskManager();
        if (taskManager != null) {
            taskManager.getManagingTasks().remove(oldtask);
        }

        taskRepository.deleteById(id);
    }



}
