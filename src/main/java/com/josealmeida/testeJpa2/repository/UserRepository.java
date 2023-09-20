package com.josealmeida.testeJpa2.repository;

import com.josealmeida.testeJpa2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
