package com.task.manager.infrastructure.command;

import java.util.Map;

@FunctionalInterface
public interface CommandFactory<T> {
    public Command<T> create(Map<String,String> args);
}
