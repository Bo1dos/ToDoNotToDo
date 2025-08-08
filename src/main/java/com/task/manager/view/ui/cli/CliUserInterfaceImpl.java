package com.task.manager.view.ui.cli;

import java.util.Scanner;

import com.task.manager.infrastructure.command.CommandDispatcher;
import com.task.manager.infrastructure.command.cli.CliCommandParser;
import com.task.manager.view.ui.UserInterface;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CliUserInterfaceImpl implements UserInterface {
    private final CommandDispatcher commandDispatcher;
    private boolean running = true;
    CliCommandParser cliCommandParser = new CliCommandParser();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while(running) {
            String input = sc.nextLine();
            // TODO - валидацию добавить
            commandDispatcher.dispatch(cliCommandParser.parseNameCommand(input), 
                                        cliCommandParser.parseArgs(input));
        }
    }
    
    public void stop() {
        running = false;
    }
}
