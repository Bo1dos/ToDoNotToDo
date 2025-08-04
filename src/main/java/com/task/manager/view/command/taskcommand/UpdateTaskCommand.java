package com.task.manager.view.command.taskcommand;

import com.task.manager.contoller.TaskController;
import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.view.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTaskCommand implements Command<TaskDTO> {

    private TaskDTO updateTaskDTO;
    private TaskController taskController;

    @Override
    public TaskDTO execute() {
        return taskController.update(updateTaskDTO);
    }
    
}
