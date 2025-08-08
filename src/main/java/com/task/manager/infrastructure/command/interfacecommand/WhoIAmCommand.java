package com.task.manager.infrastructure.command.interfacecommand;

import com.task.manager.context.UserContext;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.domain.mapper.EntityMapper;
import com.task.manager.domain.model.User;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WhoIAmCommand implements Command<UserDTO> {
    private final UserContext userContext;
    private final EntityMapper<User, UserDTO> entityMapper;

    @Override
    public UserDTO execute() {
        UserDTO currentUserDTO = entityMapper.toDto(userContext
                                                    .getUserContext()
                                                    .get());

        if (currentUserDTO == null) {
            System.out.println("No user is logged in.");
        } else {
            System.out.println("Current user: " + currentUserDTO.getUsername());
        }
        return currentUserDTO;
    }
    
}
