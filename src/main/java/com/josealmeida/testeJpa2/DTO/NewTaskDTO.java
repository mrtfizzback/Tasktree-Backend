package com.josealmeida.testeJpa2.DTO;

import com.josealmeida.testeJpa2.enums.TaskType;
import com.josealmeida.testeJpa2.model.User;

public record  NewTaskDTO(String title, String taskDescription, TaskType taskType, String taskManager) {
}
