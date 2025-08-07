package com.task.manager.service;


import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import com.task.manager.context.UserContext;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.domain.mapper.EntityMapper;
import com.task.manager.domain.model.User;
import com.task.manager.repository.UserRepository;
import com.task.manager.utils.PasswordUtils;

import lombok.AllArgsConstructor;

/**
 * Сервис для текущего юзера (работает сам над собой)
 * Сервис для управления юзерами для Админа - AdminUserService
 */
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final EntityMapper<User,UserDTO> entityMapper;
    private final UserContext userContext;




    @Override
    public User create(UserDTO dto) {

        User user = entityMapper.toEntity(dto);

        userRepository.add(user);

        return user;
    }

    // TODO
    // Придумать: нужно ли как-то ограничить поиск для юзеров
    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Обновление только текущего юзера
     */
    @Override
    public User update(UserDTO dto) {
        UUID userID = userContext.getUserContext()
                            .orElseThrow(() -> new SecurityException("Not authentificated"))
                            .getId();

        User currentUser = entityMapper.toEntity(dto);

        currentUser.setId(userID);

        if(!dto.getUsername().equals(currentUser.getUsername())){
            currentUser.setUsername(dto.getUsername());
        }

        if(dto.getRawPassword().length > 0){
            byte [] salt = PasswordUtils.generateSalt();
            byte [] hash = PasswordUtils.hash(dto.getRawPassword(), salt);

            Arrays.fill(dto.getRawPassword(), '\0');

            currentUser.setPasswordHash(Base64.getEncoder().encodeToString(hash));
            currentUser.setSalt(Base64.getEncoder().encodeToString(salt));
            
        }

        userRepository.update(currentUser);

        return currentUser;
    }

    @Override
    public void delete(UUID id) {
        UUID userID = userContext.getUserContext()
                            .orElseThrow(() -> new SecurityException("Not authentificated"))
                            .getId();
                            
        if(!userID.equals(id)){
            throw new SecurityException("Cannot delete another user.");
        }
        userRepository.delete(id);
    }


    
}
