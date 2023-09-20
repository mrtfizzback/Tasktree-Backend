package com.josealmeida.testeJpa2.DTO;
import com.josealmeida.testeJpa2.model.Task;
import com.josealmeida.testeJpa2.model.User;
import com.josealmeida.testeJpa2.model.enums.TaskType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
@Data
public class TaskDTO {

    private Long id;
    private String title;
    private String taskDescription;
    private TaskType taskType;
    private User taskCreator;
    private User taskManager;
    private Task parentTask;
    private LocalDateTime creationDate;
    private LocalDateTime lastEditedDate;
    private Set<User> taskTeam;
    private boolean isCompleted;
    private boolean isFinanced;
    private boolean isTeamComplete;
    private boolean isGovernmentApproved;
    // Other fields that you want to include from Task

    public static TaskDTO taskToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setTaskDescription(task.getTaskDescription());
        taskDTO.setTaskType(task.getTasktype());
        taskDTO.setTaskCreator(task.getTaskCreator());
        taskDTO.setTaskManager(task.getTaskManager());
        taskDTO.setTaskTeam(task.getTaskTeam());
        taskDTO.setCreationDate(task.getCreationDate());
        taskDTO.setLastEditedDate(task.getLastEditedDate());
        taskDTO.setCompleted(task.isCompleted());
        taskDTO.setFinanced(task.isFinanced());
        taskDTO.setTeamComplete(task.isTeamComplete());
        taskDTO.setGovernmentApproved(task.isGovernmentApproved());
        // Map other fields as needed
        return taskDTO;
    }

    public Task DTOtoTask() {
        Task task = new Task();
        task.setId(this.getId());
        task.setTitle(this.getTitle());
        task.setTaskDescription(this.getTaskDescription());
        task.setTasktype(this.getTaskType());
        task.setTaskCreator(this.getTaskCreator());
        task.setTaskManager(this.getTaskManager());
        task.setTaskTeam(this.getTaskTeam());
        task.setCreationDate(this.getCreationDate());
        task.setLastEditedDate(this.getLastEditedDate());
        task.setCompleted(this.isCompleted());
        task.setFinanced(this.isFinanced());
        task.setTeamComplete(this.isTeamComplete());
        task.setGovernmentApproved(this.isGovernmentApproved());
        // Map other fields as needed
        return task;
    }
}
