package com.task.manager.domain.mapper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import com.task.manager.context.UserContext;
import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.domain.model.Task;
import com.task.manager.domain.model.Task.TaskStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskMapper implements EntityMapper<Task, TaskDTO> {

    private final UserContext userContext;

    @Override
    public Task toEntity(TaskDTO dto) {
        return Task.builder()
                .id(Optional.ofNullable(dto.getId())
                                    .orElse(null))               // при update/id уже известен, при create - null
                .header(dto.getHeader())
                .description(dto.getDescription())
                .creationAt(Optional.ofNullable(dto.getDeadLine())
                                    .orElse(Instant.now()))
                .deadLine(Optional.ofNullable(dto.getDeadLine())
                                    .orElse(Instant.now().plus(1, ChronoUnit.DAYS)))
                .taskStatus(Optional.ofNullable(dto.getTaskStatus())
                                    .orElse(TaskStatus.UNDONE))
                .ownerId(userContext.getUserContext()
                                    .orElseThrow(() -> new SecurityException("Not authentificated"))
                                .getId())
                .build();
    }

    @Override
    public TaskDTO toDto(Task t) {
                return TaskDTO.builder()
                .id(t.getId())
                .header(t.getHeader())
                .description(t.getDescription())
                .creationAt(t.getCreationAt())
                .deadLine(t.getDeadLine())
                .taskStatus(t.getTaskStatus())
                .build();
    }



    
}
