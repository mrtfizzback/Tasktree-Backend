package com.josealmeida.testeJpa2.DTO;

import com.josealmeida.testeJpa2.model.Task;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.model.enums.UserType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDTO {


    private Long id;
    private String username;
    private String name;
    private String email;
    private UserType userType;
    private byte[] photoProfile;
    private Set<Long> managingTaskIds = new HashSet<>();
    private Set<Long> participatingTaskIds = new HashSet<>();

    public static UserDTO userToDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserType(user.getUserType());
        userDTO.setPhotoProfile(user.getPhotoProfile());

        // Convert sets of Task objects to sets of IDs
        if (user.getManagingTasks() != null) {
            userDTO.setManagingTaskIds(user.getManagingTasks().stream()
                    .map(Task::getId)
                    .collect(Collectors.toSet()));
        }

        if (user.getPartOfTeamTasks() != null) {
            userDTO.setParticipatingTaskIds(user.getPartOfTeamTasks().stream()
                    .map(Task::getId)
                    .collect(Collectors.toSet()));
        }

        return userDTO;
    }

    public static User dtoToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setUserType(userDTO.getUserType());
        user.setPhotoProfile(userDTO.getPhotoProfile());

        // In this method, I'm assuming Task objects should be fetched or managed elsewhere
        // So I'm not setting the ManagingTasks and ParticipatingTasks here
        // It would be more complicated to handle these, as you'd likely need to fetch the Tasks from the database or manage their state

        return user;
    }
}
