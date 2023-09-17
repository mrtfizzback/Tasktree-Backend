package com.josealmeida.testeJpa2.model;

import com.josealmeida.testeJpa2.model.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Set;

@Data
@Entity
@Table(name="Users")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String name;
    private String email;
    private UserType userType;
    @Lob
    private byte[] photoProfile;
    @ManyToMany
    private Set<Task> ManagingTasks;
    @ManyToMany
    private Set<Task> ParticipatingTasks;
}
