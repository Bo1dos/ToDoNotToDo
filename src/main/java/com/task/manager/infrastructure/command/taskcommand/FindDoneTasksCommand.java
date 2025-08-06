package com.task.manager.infrastructure.command.taskcommand;

import java.util.List;

import com.task.manager.contoller.TaskController;
import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindDoneTasksCommand implements Command<List<TaskDTO>> {

    private TaskController taskController;

    @Override
    public List<TaskDTO> execute() {
        return taskController.findDone();
    }
    
}
