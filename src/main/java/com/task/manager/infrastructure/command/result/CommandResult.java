package com.task.manager.infrastructure.command.result;

import java.util.Optional;

import lombok.AllArgsConstructor;

/**
 * Класс-обертка для результатов выполнения команд из пакета command
 * payload - результат, что вернула команда
 */
@AllArgsConstructor
public final class CommandResult<T> {

    private final T payload;
    private final String message;
    private final boolean success;


    public static <T> CommandResult<T> success(T payload) {
        if (payload == null) {
            return new CommandResult<T>(null, null, true);
        }
        return new CommandResult<T>(payload, null, true);
    }

    public static <T> CommandResult<T> successMessage(String message) {
        return new CommandResult<T>(null, message, true);
    }

    public static <T> CommandResult<T> failrue(String message) {
        return new CommandResult<T>(null, message, false);
    }

    public static <T> CommandResult<T> none() {
        return new CommandResult<T>(null, null, true);
    }


    public Optional<T> getPayload() {
        return Optional.ofNullable(payload);
    }

    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

    public boolean isSuccess() {
        return success;
    }

}
