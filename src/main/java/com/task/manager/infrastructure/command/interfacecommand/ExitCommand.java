package com.task.manager.infrastructure.command.interfacecommand;

import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExitCommand implements Command<Void> {
    private Runnable shutdownHook;

    @Override
    public Void execute() {
        shutdownHook.run();
        return null;
    }
    
}
