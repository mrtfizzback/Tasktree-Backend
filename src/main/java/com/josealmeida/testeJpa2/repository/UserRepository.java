package com.josealmeida.testeJpa2.repository;

import com.josealmeida.testeJpa2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
