package com.denysdudnik.todo_project.repository;

import com.denysdudnik.todo_project.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
