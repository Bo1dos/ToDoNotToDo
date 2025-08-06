package com.task.manager.infrastructure.command;

import java.util.List;

public class CompositeCommand implements Command<Void> {
    private final List<Command<?>> commands;

    public CompositeCommand(Command<?>... commands) {
        this.commands = List.of(commands);
    }

    public Void execute() {
        for (Command<?> c : commands) {
            c.execute();
        }
        return null;
    }
}

