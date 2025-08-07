package com.task.manager.infrastructure.command.interfacecommand;

import com.task.manager.infrastructure.command.Command;
import com.task.manager.utils.ConsoleTableUtils;

public class HelpInfoCommand implements Command<Void> {

    @Override
    public Void execute() {
        ConsoleTableUtils.printHelpMessage();
        //TODO: добавить в notifier нужный вызов
        return null;
    }
    
}
