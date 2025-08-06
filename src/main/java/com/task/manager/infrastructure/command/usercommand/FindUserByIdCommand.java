package com.task.manager.infrastructure.command.usercommand;

import java.util.Optional;
import java.util.UUID;

import com.task.manager.contoller.UserController;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindUserByIdCommand implements Command<Optional<UserDTO>> {

    private UUID findID;
    private UserController userController;

    @Override
    public Optional<UserDTO> execute() {
        return userController.findById(findID);
    }
    
}
