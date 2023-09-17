package com.josealmeida.testeJpa2.repository;

import com.josealmeida.testeJpa2.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
