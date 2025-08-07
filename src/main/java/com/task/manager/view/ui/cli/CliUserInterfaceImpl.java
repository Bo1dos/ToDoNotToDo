package com.task.manager.view.ui.cli;

import com.task.manager.infrastructure.command.CommandDispatcher;
import com.task.manager.view.ui.UserInterface;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CliUserInterfaceImpl implements UserInterface {
    private final CommandDispatcher commandDispatcher;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
    
}
