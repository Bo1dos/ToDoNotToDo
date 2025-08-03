package com.task.manager.context;

import java.util.Optional;

import com.task.manager.domain.model.User;

public interface UserContext {
    Optional<User> getUserContext();  
    
    boolean isAuthenticated();

    void setUser(User user);

    void clear();

}
