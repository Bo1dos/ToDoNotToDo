package com.task.manager.infrastructure.command;

public interface Command<T> {
    public T execute();
}
