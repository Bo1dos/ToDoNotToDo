package com.task.manager.contoller;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.task.manager.domain.dto.UserDTO;
import com.task.manager.domain.mapper.EntityMapper;
import com.task.manager.domain.model.User;
import com.task.manager.service.UserService;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final EntityMapper<User, UserDTO> entityMapper;


    public UserDTO create(UserDTO dto) {

        User user = userService.create(dto);

        return entityMapper.toDto(user);

    }


    public Optional<UserDTO> findById(UUID id) {

        Optional<User> user = userService.findById(id);

        return Optional.ofNullable(entityMapper.toDto(user.get()));

    }


    public List<UserDTO> findAll() {

        return userService.findAll().stream()
                           .map(entityMapper::toDto)
                           .collect(Collectors.toList());
    }



    public Optional<UserDTO> findByUsername(String username) {

        Optional<User> user = userService.findByUsername(username);

        return Optional.ofNullable(entityMapper.toDto(user.get()));

    }

    public UserDTO update(UserDTO dto) {

        User currentUser = userService.update(dto);

        return entityMapper.toDto(currentUser);
    }


    public void delete(UUID id) {

        userService.delete(id);

    }


}
