package com.denysdudnik.todo_project.entity;

import com.denysdudnik.todo_project.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Description must contain at least 1 symbol or letter")
    String description;

    @Enumerated(value = EnumType.ORDINAL)
    Status status;
}
