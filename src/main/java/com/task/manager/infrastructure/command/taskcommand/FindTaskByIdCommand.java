package com.task.manager.infrastructure.command.taskcommand;

import java.util.Optional;
import java.util.UUID;

import com.task.manager.contoller.TaskController;
import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTaskByIdCommand implements Command<Optional<TaskDTO>> {

    private UUID findID;
    private TaskController taskController;
    
    @Override
    public Optional<TaskDTO> execute() {
        return taskController.findById(findID);
    }
    
}
