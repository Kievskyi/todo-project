package com.denysdudnik.todo_project.entity;

import com.denysdudnik.todo_project.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Builder
@Table(name = "task", schema = "todo")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
