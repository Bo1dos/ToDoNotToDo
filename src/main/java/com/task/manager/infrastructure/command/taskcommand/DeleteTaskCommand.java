package com.task.manager.infrastructure.command.taskcommand;

import java.util.UUID;

import com.task.manager.contoller.TaskController;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTaskCommand implements Command<Void> {

    private UUID deleteID;
    private TaskController taskController;

    @Override
    public Void execute() {
        taskController.delete(deleteID);

        return null;
    }
    
}
