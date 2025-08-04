package com.task.manager.view.command.authcommand;

import com.task.manager.contoller.AuthController;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.view.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistrationCommand implements Command<Boolean> {
    
    private UserDTO registrationUser;
    private AuthController authController;

    @Override
    public Boolean execute() {
        return authController.registration(registrationUser);
    }
    
}
