package com.task.manager.view.ui.cli;

import com.task.manager.infrastructure.command.result.CommandResult;

public interface OutputHandler {
    public void handler(CommandResult<?> result);
    public void handlerError(Throwable t);
}
