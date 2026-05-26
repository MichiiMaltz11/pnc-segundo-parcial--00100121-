package com.uca.pncsegundoparcialgestiontareas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column( name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "estimatedHours", nullable = false)
    private Integer estimatedHours;

    @Column(name = "loggedHours", nullable = false)
    private Integer loggedHours;

    @Column(name = "dueDate", nullable = false)
    private LocalDate dueDate;

    @Column(name = "assignedTo", nullable = false)
    private String assignedTo;

    @Column(name = "active", nullable = false)
    private Boolean active;

}
