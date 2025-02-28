package com.springboot.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.task.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}