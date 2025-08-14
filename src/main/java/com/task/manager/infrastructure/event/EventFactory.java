package com.task.manager.infrastructure.event;

import java.util.ResourceBundle;
import java.util.UUID;

import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.infrastructure.MessageBundleProvider;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class EventFactory {
    
    private final MessageBundleProvider bundleProvider;

    public Event userRegistered(String username, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.USER_REGISTERED, resourceBundle, username, isSuccess);
    }

    public Event userLoggedIn(String username, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.USER_LOGGED_IN, resourceBundle, username, isSuccess);
    }

    public Event userLoggedOut(String username, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.USER_LOGGED_OUT, resourceBundle, username, isSuccess);
    }

    public Event taskCreated(TaskDTO task, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.TASK_CREATED, resourceBundle, task.getHeader(), isSuccess);
    }

    // public Event taskDeleted(TaskDTO task, boolean isSuccess) {
    //     return new SimpleEvent(EventType.TASK_DELETED, resourceBundle, task.getHeader(), isSuccess);
    // }

    public Event taskDeleted(UUID taskID, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.TASK_DELETED, resourceBundle, taskID, isSuccess);
    }

    public Event taskUpdated(TaskDTO task, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.TASK_UPDATED, resourceBundle, task.getHeader(), isSuccess);
    }

    public Event taskFoundById(UUID id, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.TASK_FOUND_BY_ID, resourceBundle, id.toString(), isSuccess);
    }

    public Event taskFoundAll(int count, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.TASK_FOUND_ALL, resourceBundle, count, isSuccess);
    }

    public Event taskFoundByHeader(String header, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.TASK_FOUND_BY_HEADER, resourceBundle, header, isSuccess);
    }

    public Event taskFoundDone(int count, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.TASK_FOUND_DONE, resourceBundle, String.valueOf(count), isSuccess);
    }

    public Event taskFoundUndone(int count, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.TASK_FOUND_UNDONE, resourceBundle, String.valueOf(count), isSuccess);
    }

    public Event taskFoundOverdue(int count, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.TASK_FOUND_OVERDUE, resourceBundle, String.valueOf(count), isSuccess);
    }

    public Event userCreated(String username, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.USER_CREATED, resourceBundle, username, isSuccess);
    }

    public Event userDeleted(String username, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.USER_DELETED, resourceBundle, username, isSuccess);
    }

    public Event userFoundById(UUID id, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.USER_FOUND_BY_ID, resourceBundle, id.toString(), isSuccess);
    }

    public Event userUpdated(String username, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.USER_UPDATED, resourceBundle, username, isSuccess);
    }

    public Event userFoundByUsername(String username, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.USER_FOUND_BY_USERNAME, resourceBundle, username, isSuccess);
    }

    public Event userFoundAll(int count, boolean isSuccess) {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.USER_FOUND_ALL, resourceBundle, String.valueOf(count), isSuccess);
    }


    public Event accessDenied() {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.ACCESS_DENIED, resourceBundle, "Access denied.");
    }

    public Event validationFailed() {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.VALIDATION_FAILED, resourceBundle, "Invalid input.");
    }

    public Event unexpectedError() {
        ResourceBundle resourceBundle = bundleProvider.getBundle();
        return new SimpleEvent(EventType.UNEXPECTED_ERROR, resourceBundle, "Something went wrong.");
    }
}


