package com.josealmeida.testeJpa2.service;

import com.josealmeida.testeJpa2.DTO.TaskDTO;
import com.josealmeida.testeJpa2.DTO.UserDTO;
import com.josealmeida.testeJpa2.model.Task;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.repository.TaskRepository;
import com.josealmeida.testeJpa2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UserDTO getUserById(Long id){
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return UserDTO.userToDTO(user);
        }

        return null;
    }

    public User saveUser(User newUser){
        return userRepository.save(newUser);
    }


    @Transactional
    public void deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {

            return;
        }

        User userToDelete = userOptional.get();


        for (Task managedTask : userToDelete.getManagingTasks()) {
            managedTask.setTaskManager(null);
        }

        // CRemove the user from the task's team
        for (Task teamTask : userToDelete.getPartOfTeamTasks()) {
            teamTask.getTaskTeam().remove(userToDelete);
        }

        // Clear the user's tasks lists
        userToDelete.getManagingTasks().clear();
        userToDelete.getPartOfTeamTasks().clear();

        userRepository.deleteById(id);
    }

    public TaskDTO assignNewManager(Long taskId, Long userId){
        Task task = taskRepository.findById(taskId).get();
        User user = userRepository.findById(userId).get();
        user.getManagingTasks().add(task);
        task.setTaskManager(user);
        return TaskDTO.taskToDTO(task);
    }

    public TaskDTO addUsertoTeam(Long taskId, Long userId){
        Task task = taskRepository.findById(taskId).get();
        User user = userRepository.findById(userId).get();
        user.getPartOfTeamTasks().add(task);
        task.getTaskTeam().add(user);
        return TaskDTO.taskToDTO(task);
    }
}
