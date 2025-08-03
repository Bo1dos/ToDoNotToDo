package com.task.manager.view.command;

public interface Command<T> {
    public T execute();
}
