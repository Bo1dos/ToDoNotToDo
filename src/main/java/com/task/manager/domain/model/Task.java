package com.task.manager.domain.model;

import java.time.Instant;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс-задача.
 * Содержит уникальный идентификатор id, заголовок header, 
 * подробное описание задачи description, дату создания creationAt,
 * срок сдачи задания deadline 
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    UUID id;

    @Column(name = "header", nullable = true, unique = true)
    String header;

    @Column(name = "description", nullable = true)
    String description;

    @Enumerated(EnumType.STRING)
    TaskStatus taskStatus;

    @Column(name = "creation_at")
    Instant creationAt;

    @Column(name = "dead_line")
    Instant deadLine; 

    public enum TaskStatus {
        UNDONE,
        DONE
    }

    @Column(name = "owner_id")
    UUID ownerId;
}
