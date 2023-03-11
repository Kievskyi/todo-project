package com.denysdudnik.todo_project.entity;

import com.denysdudnik.todo_project.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "task", schema = "todo")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String description;

    @Enumerated(value = EnumType.ORDINAL)
    Status status;
}
