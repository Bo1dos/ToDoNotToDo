package com.task.manager.infrastructure.command.interfacecommand;

import java.util.ResourceBundle;

import com.task.manager.infrastructure.MessageBundleProvider;
import com.task.manager.infrastructure.command.Command;
import com.task.manager.utils.ConsoleTableUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HelpInfoCommand implements Command<Void> {
    private final MessageBundleProvider messageBundleProvider;

    @Override
    public Void execute() {
        ConsoleTableUtils.printHelpMessage(messageBundleProvider.getBundle());
        //TODO: добавить в notifier нужный вызов
        return null;
    }
    
}
