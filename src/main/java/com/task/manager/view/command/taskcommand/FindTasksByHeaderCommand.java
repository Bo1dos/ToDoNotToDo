package com.task.manager.view.command.taskcommand;

import java.util.List;

import com.task.manager.contoller.TaskController;
import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.view.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindTasksByHeaderCommand implements Command<List<TaskDTO>> {

    private String header;
    private TaskController taskController;

    @Override
    public List<TaskDTO> execute() {
        return taskController.findByHeader(header);
    }
    
}
