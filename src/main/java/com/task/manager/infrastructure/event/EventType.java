package com.task.manager.infrastructure.event;

import java.util.ResourceBundle;

public enum EventType {
    
    TASK_CREATED("event.task.created"),
    TASK_UPDATED("event.task.updated"),
    TASK_DELETED("event.task.deleted"),

    TASK_FOUND_BY_ID("event.task.found_by_id"),
    TASK_FOUND_ALL("event.task.found_all"),
    TASK_FOUND_BY_HEADER("event.task.found_by_header"),
    TASK_FOUND_DONE("event.task.found_done"),
    TASK_FOUND_UNDONE("event.task.found_undone"),
    TASK_FOUND_OVERDUE("event.task.found_overdue"),

    USER_CREATED("event.user.created"),
    USER_UPDATED("event.user.updated"),
    USER_DELETED("event.user.deleted"),

    USER_FOUND_BY_ID("event.user.found_by_id"),
    USER_FOUND_BY_USERNAME("event.user.found_by_username"),
    USER_FOUND_ALL("event.user.found_all");


    // TODO
    /**
     * это ключ в .properties
     *  строка подтягивается через ResourceBundle.
     */
    private final String messageKey;

    EventType(String messageKey) {
        this.messageKey = messageKey;
    }

    public String format(ResourceBundle bundle, Object... args) {
        String pattern = bundle.getString(messageKey);
        return String.format(pattern, args);
    }
}

