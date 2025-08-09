package com.task.manager.view.ui.cli;

import java.io.Console;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.task.manager.domain.dto.TaskDTO;
import com.task.manager.domain.dto.UserDTO;
import com.task.manager.infrastructure.ShutdownManager;
import com.task.manager.infrastructure.command.CommandDispatcher;
import com.task.manager.infrastructure.command.cli.CliCommandParser;
import com.task.manager.infrastructure.command.cli.CommandValidationsRules;
import com.task.manager.infrastructure.command.result.CommandResult;
import com.task.manager.utils.ConsoleTableUtils;
import com.task.manager.view.ui.UserInterface;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CliUserInterfaceImpl implements UserInterface, OutputHandler {
    private final CommandDispatcher commandDispatcher;
    private final ShutdownManager shutdownManager;

    private final CommandValidationsRules commandValidationsRules = new CommandValidationsRules();
    private final CliCommandParser cliCommandParser = new CliCommandParser();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while(shutdownManager.isRunning()) {
            String input = sc.nextLine();

            String commandname = cliCommandParser.parseNameCommand(input);
            Map<String, String> commandArgs = cliCommandParser.parseArgs(input);
            commandArgs = commandValidationsRules.normalizeAliases(commandArgs);
            

            // TODO - валидацию переписать под локализацию
            if (!commandValidationsRules.validate(commandname, commandArgs)) {
                ConsoleTableUtils.printWarning("Вы ошиблись в написании команды, валидация не прошла");
                continue;
            }

            CommandResult<?> result =  commandDispatcher.dispatch(commandname, commandArgs);
     

        }
    }

    /**
     * Перехватываем все результаты команд, в данный момент результаты могут быть(где T - это UserDTO и TaskDTO):
     * null, List<T>, T, Optional<T>
     */
    @Override
    public void handler(CommandResult<?> result) {
        if (result == null) {
            return;
        }
        
        result.getMessage().ifPresent(ConsoleTableUtils::printInfo);

        if (!result.isSuccess() && result.getMessage().isPresent()) {
            // error case
            ConsoleTableUtils.printError(result.getMessage().get());
            return;
        }

        result.getPayload().ifPresent(payload -> {
            if (payload instanceof TaskDTO) {
                ConsoleTableUtils.printTask((TaskDTO) payload);
            } else if (payload instanceof List) {
                List<?> list = (List<?>) payload;
                if (!list.isEmpty() && list.get(0) instanceof TaskDTO) {
                    //noinspection unchecked
                    ConsoleTableUtils.printTasks((List<TaskDTO>) list);
                } else if (!list.isEmpty() && list.get(0) instanceof UserDTO) {
                    // тут можно добавить печать пользователей или что-то такое, пока не используем
                    // ConsoleTableUtils.printUsers((List<UserDTO>) list);
                } else {
                    // fallback печати коллекций
                    ConsoleTableUtils.printInfo(list.toString());
                }
            } else if (payload instanceof UserDTO) {
                // например печать краткой инфы о юзере
                ConsoleTableUtils.printInfo("User: " + ((UserDTO) payload).getUsername());
            } else {
                // универсальный fallback
                ConsoleTableUtils.printInfo(String.valueOf(payload));
            }
        });
    }

    @Override
    public void handlerError(Throwable t) {
        ConsoleTableUtils.printError("Internal error" + t.getMessage());
        //TODO: переписать нормально обработку ошибок везде и локализацию
    }
    

}
