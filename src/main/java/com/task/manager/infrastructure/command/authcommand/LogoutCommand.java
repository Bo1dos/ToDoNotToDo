package com.task.manager.infrastructure.command.authcommand;

import com.task.manager.contoller.AuthController;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogoutCommand implements Command<Boolean> {

    private AuthController authController;

    @Override
    public Boolean execute() {
        return authController.logout();
    }
    
}