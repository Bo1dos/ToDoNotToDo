package com.task.manager.infrastructure.event;

import java.util.ResourceBundle;
import java.util.UUID;

import com.task.manager.domain.dto.TaskDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class EventFactory {
    
    private final ResourceBundle resourceBundle;

    public Event userRegistered(String username) {
        return new SimpleEvent(EventType.USER_REGISTERED, resourceBundle, username);
    }

    public Event userLoggedIn(String username) {
        return new SimpleEvent(EventType.USER_LOGGED_IN, resourceBundle, username);
    }

    public Event userLoggedOut(String username) {
        return new SimpleEvent(EventType.USER_LOGGED_OUT, resourceBundle, username);
    }

    public Event taskCreated(TaskDTO task) {
        return new SimpleEvent(EventType.TASK_CREATED, resourceBundle, task.getHeader());
    }

    public Event taskDeleted(TaskDTO task) {
        return new SimpleEvent(EventType.TASK_DELETED, resourceBundle, task.getHeader());
    }

    public Event taskUpdated(TaskDTO task) {
        return new SimpleEvent(EventType.TASK_UPDATED, resourceBundle, task.getHeader());
    }

    public Event userCreated(String username) {
        return new SimpleEvent(EventType.USER_CREATED, resourceBundle, username);
    }

    public Event userDeleted(String username) {
        return new SimpleEvent(EventType.USER_DELETED, resourceBundle, username);
    }

    public Event userFoundById(UUID id) {
        return new SimpleEvent(EventType.USER_FOUND_BY_ID, resourceBundle, id.toString());
    }

    // TODO
    // Прописать под все команды
}


