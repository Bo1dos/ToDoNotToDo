package com.task.manager.service;

import java.util.Arrays;
import java.util.Optional;

import com.task.manager.context.UserContext;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.domain.model.User;
import com.task.manager.repository.UserRepository;
import com.task.manager.utils.PasswordUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private UserContext userContext;
    private UserService userService;

    @Override
    public boolean registration(UserDTO userDTO) {
        User user = userService.create(userDTO);

        if(user.getId() != null){
            return true;
        }

        return false;

    }

    @Override
    public boolean login(UserDTO userDTO) {
        String username = new String(userDTO.getUsername()); 
        char[] rawPassword = userDTO.getRawPassword();
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty()) { 
            return false; 
        }

        if(PasswordUtils.verify(rawPassword, user.get().getPasswordHash(), user.get().getSalt())){
            Arrays.fill(rawPassword, '\0');
            return true;
        }
        Arrays.fill(rawPassword, '\0');
        

        return false;

    }

    @Override
    public boolean logout() {

        if (userContext.isAuthenticated()){
            userContext.clear();
            
            return true;
        }
        return false;
    }
    
}
