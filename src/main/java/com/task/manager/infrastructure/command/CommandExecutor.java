package com.task.manager.infrastructure.command;


import com.task.manager.infrastructure.command.result.CommandResult;
import com.task.manager.infrastructure.event.EventFactory;
import com.task.manager.infrastructure.notification.NotificationService;
import com.task.manager.utils.ExceptionUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandExecutor {

    private final NotificationService notificationService;
    private final EventFactory eventFactory;

    public <T> CommandResult<T> execute(Command<T> command) {
        try {
            T raw = command.execute();
            
            
            if (raw == null) {
                return CommandResult.none();
            }

            if (raw instanceof CommandResult){
                return (CommandResult<T>) raw;
            }

            return CommandResult.success(raw);


        } catch (SecurityException e) {
            notificationService.publish(eventFactory.accessDenied());
            
            //TODO: переписать под логгер
            ExceptionUtils.getStackTraceAsString(e);
            return CommandResult.failrue(eventFactory.accessDenied().message());
        } catch (IllegalArgumentException e) {
            notificationService.publish(eventFactory.validationFailed());
            //TODO: переписать под логгер

            ExceptionUtils.getStackTraceAsString(e);
            return CommandResult.failrue(eventFactory.validationFailed().message());
        } catch (Exception e) {
            notificationService.publish(eventFactory.unexpectedError());

            //TODO: переписать под логгер
            ExceptionUtils.getStackTraceAsString(e);
            return CommandResult.failrue(eventFactory.unexpectedError().message());
        }
    }

    
    
}
