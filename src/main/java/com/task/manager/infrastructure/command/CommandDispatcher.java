package com.task.manager.infrastructure.command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.task.manager.infrastructure.command.result.CommandResult;


public class CommandDispatcher {
    private final Map<String, CommandFactory> registry = new HashMap<>();
    private final CommandExecutor commandExecutor;


    public CommandDispatcher(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public <T> void register(String name, CommandFactory commandFactory) {
        registry.put(name, commandFactory);
    }


    @SuppressWarnings("unchecked")
    public <T> CommandResult<T> dispatch(String name, Map<String, String> args) {
        CommandFactory commandFactory = registry.get(name);
        if (commandFactory == null){
            throw new IllegalArgumentException("No command registered with name: " + name);
        }

        Command<T> command = (Command<T>) commandFactory.create(args);

        return commandExecutor.execute(command);
    }
}
