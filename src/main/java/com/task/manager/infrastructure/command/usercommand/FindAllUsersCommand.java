package com.task.manager.infrastructure.command.usercommand;

import java.util.List;

import com.task.manager.contoller.UserController;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindAllUsersCommand implements Command<List<UserDTO>> {

    private UserController userController;

    @Override
    public List<UserDTO> execute() {
        return userController.findAll();
    }
    
}
