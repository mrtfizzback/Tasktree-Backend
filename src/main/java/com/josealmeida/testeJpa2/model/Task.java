//package com.josealmeida.testeJpa2.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.josealmeida.testeJpa2.model.enums.TaskType;
//import com.josealmeida.testeJpa2.model.enums.TaskType;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@Entity
//@Table(name="Tasks")
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Task {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String title;
//    private String taskDescription;
//
//    @Enumerated(EnumType.STRING)
//    private TaskType tasktype;
//
//    @ManyToOne
//    @JoinColumn(name = "creator_id")
//    private User taskCreator;
//
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "Manager_id", referencedColumnName = "id")
//    private User taskManager;
//    private LocalDateTime creationDate;
//    private LocalDateTime lastEditedDate;
//
//    @JsonIgnoreProperties({"ManagingTasks", "PartOfTeamTasks"})
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "user_in_team",
//            joinColumns = @JoinColumn(name = "task_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private Set<User> taskTeam = new HashSet<>();
//
//    @ManyToOne
//    @JoinColumn(name = "parent_task_id")
//    @JsonManagedReference
//    private Task parentTask;
//
//    @OneToMany(mappedBy = "parentTask")
//    @JsonBackReference
//    private Set<Task> childTasks = new HashSet<>();
//    private boolean isCompleted;
//    private boolean isFinanced;
//    private boolean isTeamComplete;
//    private boolean isGovernmentApproved;
//}





package com.josealmeida.testeJpa2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.josealmeida.testeJpa2.enums.TaskType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="Tasks")
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String taskDescription;
    private LocalDateTime creationDate;
    private LocalDateTime lastEditedDate;
    private boolean isCompleted;
    private boolean isFinanced;
    private boolean isTeamComplete;
    private boolean isGovernmentApproved;

    // New fields
    private int taskOrder;
    private int taskLevel;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User taskCreator;

    @ManyToOne
    @JoinColumn(name = "Manager_id", referencedColumnName = "id")
    private User taskManager;

    @ManyToMany
    @JoinTable(
            name = "user_in_team",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> taskTeam = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    @JsonBackReference
    private Task parentTask;


    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference

    @JsonManagedReference

    private Set<Task> childTasks = new HashSet<>();

    private Task(String title, String taskDescription, TaskType taskType) {
        this.title = title;
        this.taskDescription = taskDescription;
        this.taskType = taskType;

    }


}
