//package com.josealmeida.testeJpa2.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.josealmeida.testeJpa2.model.enums.UserType;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@Entity
//@Table(name="Users")
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String username;
//    private String name;
//    private String email;
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private UserType userType;
//
//    @Lob
//    private byte[] photoProfile;
//
//    @JsonIgnoreProperties({"taskManager"})
//    @JsonIgnore
//    @OneToMany(mappedBy = "taskManager")
//    private Set<Task> ManagingTasks = new HashSet<>();
//
//    @JsonIgnoreProperties({"taskTeam"})
//    @JsonIgnore
//    @ManyToMany(mappedBy = "taskTeam")
//    private Set<Task> PartOfTeamTasks = new HashSet<>();
//}


package com.josealmeida.testeJpa2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.josealmeida.testeJpa2.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

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
    private String userName;
    private String email;
    private String password;

    @Getter
    private String roles;

//    @Getter
//    private String role;



//    public User(String userName, String password, UserRole role){
//        this.userName = userName;
//        this.password = password;
//        this.role = role;
//    }

    public User(String userName, String password, String roles){
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }




    @JsonIgnoreProperties({"taskManager"})
    @OneToMany(mappedBy = "taskManager", fetch = FetchType.LAZY)
    private Set<Task> managingTasks = new HashSet<>();

    @JsonIgnoreProperties({"taskTeam"})
    @ManyToMany(mappedBy = "taskTeam", fetch = FetchType.LAZY)
    private Set<Task> partOfTeamTasks = new HashSet<>();
}
