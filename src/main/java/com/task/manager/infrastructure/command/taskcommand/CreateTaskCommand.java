package com.task.manager.infrastructure.command.taskcommand;


import com.task.manager.contoller.TaskController;
import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTaskCommand implements Command<TaskDTO> {
    private TaskDTO inputTaskDTO;
    private TaskController taskController;

    @Override
    public TaskDTO execute() {
        return taskController.create(inputTaskDTO);

    }
    
}
