package com.example.TodoApp.Repository;

import com.example.TodoApp.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> { }