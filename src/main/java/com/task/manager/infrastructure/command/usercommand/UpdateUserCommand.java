package com.task.manager.infrastructure.command.usercommand;

import com.task.manager.contoller.UserController;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateUserCommand implements Command<UserDTO> {

    private UserDTO updateUser;
    private UserController userController;

    @Override
    public UserDTO execute() {
       return userController.update(updateUser);
    }
    
}
