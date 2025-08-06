package com.task.manager.contoller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.domain.mapper.EntityMapper;
import com.task.manager.domain.model.Task;
import com.task.manager.infrastructure.event.EventFactory;
import com.task.manager.infrastructure.notification.NotificationService;
import com.task.manager.service.TaskSevice;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class TaskController {

    private final TaskSevice taskSevice;
    private final EntityMapper<Task, TaskDTO> entityMapper;

    private final NotificationService notificationService;
    private final EventFactory eventFactory;


    public TaskDTO create(TaskDTO dto) {
        Task task = taskSevice.create(dto);

        if (task == null) {
            notificationService.publish(eventFactory.taskCreated(dto, false));
        } 
        
        notificationService.publish(eventFactory.taskCreated(dto, true));

        return entityMapper.toDto(task);
    }


    public Optional<TaskDTO> findById(UUID id) {
        Optional<Task> task = taskSevice.findById(id);

        if (task == null) {
            notificationService.publish(eventFactory.taskFoundById(id, false));
        }

        notificationService.publish(eventFactory.taskFoundById(id, true));

        return Optional.ofNullable(entityMapper
                                .toDto(task
                                        .get()));
    }

    public List<TaskDTO> findAll() {
        List<Task> allTask = taskSevice.findAll();

        int size = allTask.size();
        if (size == 0){
            notificationService.publish(eventFactory.taskFoundAll(size, false));
        }
        
        notificationService.publish(eventFactory.taskFoundAll(size, true));

        return allTask.stream()
                        .map(entityMapper::toDto)
                        .collect(Collectors.toList());
    }


    public List<TaskDTO> findByHeader(String header) {
        List<Task> findByheader = taskSevice.findByHeader(header);

        int size = findByheader.size();
        if (size == 0) {
            notificationService.publish(eventFactory.taskFoundByHeader(header, false));
        }

        notificationService.publish(eventFactory.taskFoundByHeader(header, true));

        return findByheader.stream()
                        .map(entityMapper::toDto)
                        .collect(Collectors.toList());
    }


    public List<TaskDTO> findDone() {
        List<Task> findDone = taskSevice.findDone();
        
        int size = findDone.size();
        if (size == 0) {
            notificationService.publish(eventFactory.taskFoundDone(size, false));
        }

        notificationService.publish(eventFactory.taskFoundDone(size, true));

        return findDone.stream()
                        .map(entityMapper::toDto)
                        .collect(Collectors.toList());
    }


    public List<TaskDTO> findUndone() {
        List<Task> findUndone = taskSevice.findUndone();

        int size = findUndone.size();
        if(size == 0) {
            notificationService.publish(eventFactory.taskFoundUndone(size, false));
        }

        notificationService.publish(eventFactory.taskFoundUndone(size, true));

        return findUndone.stream()
                        .map(entityMapper::toDto)
                        .collect(Collectors.toList());
    }

    public List<TaskDTO> findOverdue() {
        List<Task> findOverdue = taskSevice.findOverdue();

        int size = findOverdue.size();
        if(size == 0) {
            notificationService.publish(eventFactory.taskFoundOverdue(size, false));
        }

        notificationService.publish(eventFactory.taskFoundOverdue(size, true));

        return findOverdue.stream()
                        .map(entityMapper::toDto)
                        .collect(Collectors.toList());
    }


    public TaskDTO update(TaskDTO dto) {
        Task task = taskSevice.update(dto);

        if (task == null) {
            notificationService.publish(eventFactory.taskUpdated(dto, false));
        }

        notificationService.publish(eventFactory.taskUpdated(dto, true));

        return entityMapper.toDto(task);

    }


    public void delete(UUID id) {
        taskSevice.delete(id);
        
        notificationService.publish(eventFactory.taskDeleted(id, true));
    }
  


}
