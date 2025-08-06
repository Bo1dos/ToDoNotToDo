package com.task.manager.infrastructure.command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import lombok.AllArgsConstructor;

public class CommandDispatcher {
    private final Map<String, Supplier<? extends Command<?>>> registry = new HashMap<>();
    private final CommandExecutor commandExecutor;


    public CommandDispatcher(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public <T> void register(String name, Supplier<Command<T>> supplier) {
        registry.put(name, supplier);
    }


    @SuppressWarnings("unchecked")
    public <T> T dispatch(String name) {
        Supplier<? extends Command<?>> supplier = registry.get(name);
        if (supplier == null){
            throw new IllegalArgumentException("No command registered with name: " + name);
        }

        Command<T> command = (Command<T>) registry.get(supplier);

        return commandExecutor.execute(command);
    }
}
