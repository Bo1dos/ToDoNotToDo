package com.task.manager.repository;

import java.util.Optional;
import java.util.UUID;


import com.task.manager.domain.model.User;

public interface UserRepository extends Repository<User, UUID> {
    Optional<User> findByUsername(String username);
}
