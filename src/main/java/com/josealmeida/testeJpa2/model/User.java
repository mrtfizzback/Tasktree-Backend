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
import com.josealmeida.testeJpa2.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Getter
@Setter
@Entity
@Table(name="Users")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String login;

    @Enumerated(EnumType.STRING)
    private UserRole role;

//    @Lob
//    private byte[] photoProfile;

    @JsonIgnoreProperties({"taskManager"})
    @OneToMany(mappedBy = "taskManager", fetch = FetchType.LAZY)
    private Set<Task> managingTasks = new HashSet<>();

    @JsonIgnoreProperties({"taskTeam"})
    @ManyToMany(mappedBy = "taskTeam", fetch = FetchType.LAZY)
    private Set<Task> partOfTeamTasks = new HashSet<>();

    public User(String login, String encryptedPassword, String role) {
        this.login = login;
        this.password = encryptedPassword;
        this.role = UserRole.valueOf(role);
    }

    @Override
    public String getUsername(){
        return login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_FREE_USER"), new SimpleGrantedAuthority("ROLE_PREMIUM"));
        else return List.of();

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
