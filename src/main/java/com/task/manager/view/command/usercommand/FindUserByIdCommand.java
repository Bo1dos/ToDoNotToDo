package com.task.manager.view.command.usercommand;

import java.util.Optional;

import com.task.manager.domain.dto.UserDTO;
import com.task.manager.view.command.Command;

public class FindUserByIdCommand implements Command<Optional<UserDTO>> {

    @Override
    public Optional<UserDTO> execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
