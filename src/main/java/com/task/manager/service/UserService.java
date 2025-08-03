package com.task.manager.service;

import java.util.Optional;
import java.util.UUID;

import com.task.manager.domain.dto.UserDTO;
import com.task.manager.domain.model.User;

public interface UserService extends Service<User, UserDTO, UUID> {
    public Optional<User> findByUsername(String username);

}
