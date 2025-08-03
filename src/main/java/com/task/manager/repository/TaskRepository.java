package com.task.manager.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.task.manager.domain.model.Task;
import com.task.manager.domain.model.Task.TaskStatus;

public interface TaskRepository extends Repository<Task, UUID> {
    List<Task> findByHeader(String header);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findOverdue();

    Integer countDone();
    Integer countUndone();
    Integer countOverdue();
}
