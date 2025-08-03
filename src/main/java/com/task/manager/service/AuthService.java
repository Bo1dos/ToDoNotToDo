package com.task.manager.service;

import com.task.manager.domain.dto.UserDTO;

public interface AuthService {

    public boolean login(UserDTO userDTO);
    public boolean logout();

}
