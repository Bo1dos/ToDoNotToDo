package com.task.manager.infrastructure.command.usercommand;

import java.util.Optional;

import com.task.manager.contoller.UserController;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindUserByUsernameCommand implements Command<Optional<UserDTO>> {

    private String findUsername;
    private UserController userController;

    @Override
    public Optional<UserDTO> execute() {
        return userController.findByUsername(findUsername);
    }
    
}
