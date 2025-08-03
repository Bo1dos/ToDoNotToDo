package com.task.manager.contoller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.domain.mapper.EntityMapper;
import com.task.manager.domain.model.Task;
import com.task.manager.service.TaskSevice;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class TaskController {
    private final TaskSevice taskSevice;
    private final EntityMapper<Task, TaskDTO> entityMapper;


    public TaskDTO create(TaskDTO dto) {
        Task task = taskSevice.create(dto);

        return entityMapper.toDto(task);
    }


    public Optional<TaskDTO> findById(UUID id) {
        Optional<Task> task = taskSevice.findById(id);

        return Optional.ofNullable(entityMapper
                                .toDto(task
                                        .get()));
    }

    public List<TaskDTO> findAll() {

       return taskSevice.findAll().stream()
                        .map(entityMapper::toDto)
                        .collect(Collectors.toList());
    }


    public List<TaskDTO> findByHeader(String header) {
        return taskSevice.findByHeader(header).stream()
                        .map(entityMapper::toDto)
                        .collect(Collectors.toList());
    }


    public List<TaskDTO> findDone() {
       return taskSevice.findDone().stream()
                        .map(entityMapper::toDto)
                        .collect(Collectors.toList());
    }


    public List<TaskDTO> findUndone() {
        return taskSevice.findUndone().stream()
                        .map(entityMapper::toDto)
                        .collect(Collectors.toList());
    }

    public List<TaskDTO> findOverdue() {
        return taskSevice.findOverdue().stream()
                        .map(entityMapper::toDto)
                        .collect(Collectors.toList());
    }


    public TaskDTO update(TaskDTO dto) {
       Task task = taskSevice.update(dto);

       return entityMapper.toDto(task);

    }


    public void delete(UUID id) {
        taskSevice.delete(id);
    }
  


}
