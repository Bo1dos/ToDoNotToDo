package com.task.manager.contoller;

import com.task.manager.domain.dto.UserDTO;
import com.task.manager.service.AuthService;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    // TODO добавить локализацию и команды 
    public boolean login(String username, char[] rawPassword) {
        UserDTO userDTO = new UserDTO(username, rawPassword);

        if(authService.login(userDTO)){
            return true;
        }

        return false;

    }

    public boolean logout() {

        if(authService.logout()){
            return true;
        }

        return false;
    }
}
