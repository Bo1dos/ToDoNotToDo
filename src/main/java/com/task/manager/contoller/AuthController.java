package com.task.manager.contoller;

import com.task.manager.domain.dto.UserDTO;
import com.task.manager.infrastructure.event.EventFactory;
import com.task.manager.infrastructure.notification.NotificationService;
import com.task.manager.service.AuthService;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final NotificationService notificationService;
    private final EventFactory eventFactory;

    // TODO добавить локализацию и команды
    public boolean registration(UserDTO userDTO) {

        if(authService.registration(userDTO)){
            notificationService.publish(eventFactory.userRegistered(userDTO.getUsername(), true));
            return true;
        }

        notificationService.publish(eventFactory.userRegistered(userDTO.getUsername(), false));
        return false;

    }

    public boolean login(UserDTO userDTO) {

        if(authService.login(userDTO)){
            notificationService.publish(eventFactory.userLoggedIn(userDTO.getUsername(), true));
            return true;
        }

        notificationService.publish(eventFactory.userLoggedIn(userDTO.getUsername(), false));
        return false;

    }

    public boolean logout() {

        if(authService.logout()){
            notificationService.publish(eventFactory.userLoggedOut("TODO", true));
            return true;
        }

        notificationService.publish(eventFactory.userLoggedOut("TODO", false));
        return false;
    }
}
