package com.task.manager.view.command.taskcommand;

import java.util.Optional;

import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.view.command.Command;

public class FindTaskByIdCommand implements Command<Optional<TaskDTO>> {

    @Override
    public Optional<TaskDTO> execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
