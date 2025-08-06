package com.task.manager.contoller;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.task.manager.domain.dto.UserDTO;
import com.task.manager.domain.mapper.EntityMapper;
import com.task.manager.domain.model.User;
import com.task.manager.infrastructure.event.EventFactory;
import com.task.manager.infrastructure.notification.NotificationService;
import com.task.manager.service.UserService;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final EntityMapper<User, UserDTO> entityMapper;

    private final NotificationService notificationService;
    private final EventFactory eventFactory;


    public UserDTO create(UserDTO dto) {

        User user = userService.create(dto);

        if (user == null) {
            notificationService.publish(eventFactory.userCreated("None", false));
        }

        notificationService.publish(eventFactory.userCreated(user.getUsername(), true));

        return entityMapper.toDto(user);

    }


    public Optional<UserDTO> findById(UUID id) {

        Optional<User> user = userService.findById(id);

        if (user.isEmpty()) {
            notificationService.publish(eventFactory.userFoundById(id, false));
        }

        notificationService.publish(eventFactory.userFoundById(id, true));

        return Optional.ofNullable(entityMapper.toDto(user.get()));

    }


    public List<UserDTO> findAll() {
        List<User> findAllUsers = userService.findAll();

        int size = findAllUsers.size();
        if (size == 0) {
            notificationService.publish(eventFactory.userFoundAll(size, false));
        }

        notificationService.publish(eventFactory.userFoundAll(size, true));

        return findAllUsers.stream()
                           .map(entityMapper::toDto)
                           .collect(Collectors.toList());
    }



    public Optional<UserDTO> findByUsername(String username) {

        Optional<User> user = userService.findByUsername(username);

        if (user.isEmpty()) {
            notificationService.publish(eventFactory.userFoundByUsername(username, false));
        }

        notificationService.publish(eventFactory.userFoundByUsername(username, true));

        return Optional.ofNullable(entityMapper.toDto(user.get()));

    }

    public UserDTO update(UserDTO dto) {

        User currentUser = userService.update(dto);

        if (currentUser == null) {
            notificationService.publish(eventFactory.userUpdated(dto.getUsername(), false));
        }

        notificationService.publish(eventFactory.userUpdated(dto.getUsername(), true));

        return entityMapper.toDto(currentUser);
    }


    public void delete(UUID id) {
        String username = findById(id).get().getUsername();
        
        userService.delete(id);

        notificationService.publish(eventFactory.userDeleted(username, true));

    }


}
