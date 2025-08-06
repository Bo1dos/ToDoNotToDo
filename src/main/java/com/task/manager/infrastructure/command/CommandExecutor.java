package com.task.manager.infrastructure.command;


import com.task.manager.infrastructure.event.EventFactory;
import com.task.manager.infrastructure.notification.NotificationService;
import com.task.manager.utils.ExceptionUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandExecutor {

    private final NotificationService notificationService;
    private final EventFactory eventFactory;

    public <T> T execute(Command<T> command) {
        try {
            return command.execute();
        } catch (SecurityException e) {
            notificationService.publish(eventFactory.accessDenied());

            //TODO: переписать под логгер
            ExceptionUtils.getStackTraceAsString(e);
        } catch (IllegalArgumentException e) {
            notificationService.publish(eventFactory.validationFailed());

            //TODO: переписать под логгер
            ExceptionUtils.getStackTraceAsString(e);
        } catch (Exception e) {
            notificationService.publish(eventFactory.unexpectedError());

            //TODO: переписать под логгер
            ExceptionUtils.getStackTraceAsString(e);
        }
        return null;
    }

    
    
}
