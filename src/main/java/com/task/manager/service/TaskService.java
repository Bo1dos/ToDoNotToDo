package com.task.manager.service;

import java.util.List;
import java.util.UUID;

import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.domain.model.Task;

public interface TaskService extends Service<Task, TaskDTO, UUID> {
    public List<Task> findByHeader(String header);
    public List<Task> findDone();
    public List<Task> findUndone();
    public List<Task> findOverdue();
    
}
