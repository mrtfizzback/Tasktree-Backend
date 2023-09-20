package com.josealmeida.testeJpa2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.josealmeida.testeJpa2.model.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="Users")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Lob
    private byte[] photoProfile;

    @JsonIgnoreProperties({"taskManager"})
    @JsonIgnore
    @OneToMany(mappedBy = "taskManager")
    private Set<Task> ManagingTasks = new HashSet<>();

    @JsonIgnoreProperties({"taskTeam"})
    @JsonIgnore
    @ManyToMany(mappedBy = "taskTeam")
    private Set<Task> PartOfTeamTasks = new HashSet<>();
}
