package com.task.manager.infrastructure.command.authcommand;

import com.task.manager.contoller.AuthController;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginCommand implements Command<Boolean> {

    private UserDTO loginUser;
    private AuthController authController;

    @Override
    public Boolean execute() {
        return authController.login(loginUser);
    }
    
}
