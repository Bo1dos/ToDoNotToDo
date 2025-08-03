package com.task.manager.context;

import java.util.Optional;

import com.task.manager.domain.model.User;

public class UserContextImpl implements UserContext{
    
    private User currentUser;

    /**
     * Метод для получения текущего юзера (в обертке)
     */
    @Override
    public Optional<User> getUserContext() {
        return Optional.ofNullable(currentUser);
    }


    /**
     * Метод для проверки аутентифицирован ли пользователь 
     */
    // Будет вызываться перед каждым действием
    @Override
    public boolean isAuthenticated() {
        return currentUser != null;
    }


    /**
     * Метод для назначения юзера
     */
    @Override
    public void setUser(User user) {
        currentUser = user;
    }

    /**
     * Метод, что вызывается  при выходе пользователя (логауте),
     * удаляет текущего пользователя
     */
    @Override
    public void clear() {
        currentUser = null;
    }
    
}
