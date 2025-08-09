package com.task.manager.infrastructure.command.interfacecommand;

import com.task.manager.infrastructure.ShutdownManager;
import com.task.manager.infrastructure.command.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExitCommand implements Command<Void> {
    private ShutdownManager shutdownManager;

    @Override
    public Void execute() {
        shutdownManager.requestShutdown();
        return null;
    }
    
}
