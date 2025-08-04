package com.task.manager.view.command.authcommand;

import com.task.manager.contoller.AuthController;
import com.task.manager.view.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogoutCommand implements Command<Boolean> {

    private AuthController authController;

    @Override
    public Boolean execute() {
        return authController.logout();
    }
    
}