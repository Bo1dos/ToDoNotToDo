package com.task.manager.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.task.manager.context.UserContext;
import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.domain.mapper.EntityMapper;
import com.task.manager.domain.model.Task;
import com.task.manager.domain.model.Task.TaskStatus;
import com.task.manager.repository.TaskRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskSeviceImpl implements TaskSevice {

    private final TaskRepository taskRepository;
    private final EntityMapper<Task, TaskDTO> entityMapper;
    private final UserContext userContext;


    @Override
    public Task create(TaskDTO dto) {
        Task task = entityMapper.toEntity(dto);
        
        taskRepository.add(task);
        return task;
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findByHeader(String header) {
        UUID currentUserId = userContext.getUserContext().get().getId();
        return taskRepository.findByHeader(header).stream()
                            .filter(task -> task.getOwnerId().equals(currentUserId))
                            .toList();
    }

    @Override
    public List<Task> findDone() {
        UUID currentUserId = userContext.getUserContext().get().getId();
        return taskRepository.findByStatus(TaskStatus.DONE).stream()
                            .filter(task -> task.getOwnerId().equals(currentUserId))
                            .toList();
    }

    @Override
    public List<Task> findUndone() {
        UUID currentUserId = userContext.getUserContext().get().getId();
        return taskRepository.findByStatus(TaskStatus.UNDONE).stream()
                            .filter(task -> task.getOwnerId().equals(currentUserId))
                            .toList();
    }

    @Override
    public List<Task> findOverdue() {
        UUID currentUserId = userContext.getUserContext().get().getId();
        return taskRepository.findOverdue().stream()
                            .filter(task -> task.getOwnerId().equals(currentUserId))
                            .toList();
    }

    @Override
    public Task update(TaskDTO dto) {

        Task task = entityMapper.toEntity(dto);
        UUID currentUserId = userContext.getUserContext().get().getId();

        if(!task.getOwnerId().equals(currentUserId)){
            throw new SecurityException("Cannot update foreign task.");
        }

        taskRepository.update(task);

        return task;

    }

    @Override
    public void delete(UUID id) {
        UUID currentUserId = userContext.getUserContext().get().getId();

        Optional<Task> task = findById(id);
        if(!task.get().getOwnerId().equals(currentUserId)){
            throw new SecurityException("Cannot delete foreign task.");
        }

        taskRepository.delete(id);
    }
  

}
