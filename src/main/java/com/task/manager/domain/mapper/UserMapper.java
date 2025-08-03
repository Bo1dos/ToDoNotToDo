package com.task.manager.domain.mapper;

import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import com.task.manager.context.UserContext;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.domain.model.User;
import com.task.manager.utils.PasswordUtils;

import lombok.AllArgsConstructor;

//TODO
//Сделать обработку случаев, когда маппер принимает нулл - где - не знаю
@AllArgsConstructor
public class UserMapper implements EntityMapper<User, UserDTO> {

    private final UserContext userContext;

    /**
     * Из ДТО в Сущность {@Code User} - без ID (он null)
     */
     @Override
    public User toEntity(UserDTO dto) {
        return toEntity(dto, null); // если нет ID
    }

    public User toEntity(UserDTO dto, UUID userId) {
        
        byte[] salt = PasswordUtils.generateSalt();
        byte[] hash = PasswordUtils.hash(dto.getRawPassword(), salt);

        Arrays.fill(dto.getRawPassword(), '\0'); // очистка сырого пароля

        return User.builder()
                .id(userId)
                .username(dto.getUsername())
                .passwordHash(Base64.getEncoder().encodeToString(hash))
                .salt(Base64.getEncoder().encodeToString(salt))
                .build();
    }

    /**
     * Из Сущности в ДТО {@Code UserDTO} - без пароля
     */
    @Override
    public UserDTO toDto(User entity) {
        return UserDTO.builder()
                .username(entity.getUsername())
                .build();
    }




}
