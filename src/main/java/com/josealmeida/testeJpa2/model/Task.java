package com.josealmeida.testeJpa2.model;

import com.josealmeida.testeJpa2.model.enums.TaskType;
import com.josealmeida.testeJpa2.model.enums.TaskType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name="Tasks")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String taskDescription;
    private TaskType tasktype;
    @ManyToOne
    private User taskCreator;
    @ManyToOne
    private User taskManager;
    private LocalDateTime creationDate;
    private LocalDateTime lastEditedDate;
    @ManyToMany
    private Set<User> taskTeam;
    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;
    private boolean isCompleted;
    private boolean isFinanced;
    private boolean isTeamComplete;
    private boolean isGovernmentApproved;
}
