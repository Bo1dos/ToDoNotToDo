package com.task.manager.infrastructure.command.usercommand;

import java.util.UUID;

import com.task.manager.contoller.UserController;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteUserCommand implements Command<Void> {

    private UUID deleteID;
    private UserController userController;

    @Override
    public Void execute() {
        userController.delete(deleteID);
        return null;
        
    }
    
}
