package com.task.manager.infrastructure.command.cli;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandValidationsRules {


    /**
     * Необходимые аргументы для команды (команда - ключ, Set - необходимые аргументы для команды)
     */
    Map<String, Set<String>> requiredArgs = Map.ofEntries(
        Map.entry("register", Set.of("username", "password")),
        Map.entry("login", Set.of("username", "password")),
        Map.entry("logout", Set.of()),

        Map.entry("create-task", Set.of()), // всё необязательно
        Map.entry("update-task", Set.of("id")),
        Map.entry("delete-task", Set.of("id")),
        Map.entry("show-task", Set.of("id")),
        Map.entry("find-task-by-header", Set.of("header")),

        Map.entry("list-tasks", Set.of()),
        Map.entry("list-done", Set.of()),
        Map.entry("list-undone", Set.of()),
        Map.entry("list-overdue", Set.of()),

        Map.entry("help", Set.of()),
        Map.entry("whoami", Set.of()),
        Map.entry("clear", Set.of()),
        Map.entry("exit", Set.of())
    );

    /**
     * Возможные аргументы для команды (команда - ключ, Set - аргументы для команды, которые можно использовать)
     */
    Map<String, Set<String>> allowedArgs = Map.ofEntries(
        Map.entry("register", Set.of("username", "password")),
        Map.entry("login", Set.of("username", "password")),
        Map.entry("logout", Set.of()),
        
        Map.entry("create-task", Set.of("header", "desc", "deadline")),
        Map.entry("update-task", Set.of("id", "header", "desc", "deadline")),
        Map.entry("delete-task", Set.of("id")),
        Map.entry("show-task", Set.of("id")),
        Map.entry("find-task-by-header", Set.of("header"))
        
    );

    /**
     * Алиасы для флагов (алиас - ключ, значение - флаг)
     */
    Map<String, String> aliases = Map.ofEntries(
        Map.entry("u", "username"),
        Map.entry("p", "password"),

        Map.entry("hd", "header"),
        Map.entry("d", "desc"),
        Map.entry("dl", "deadline")

    );



    /**
     * Проверяет: содержатся ли все необходимые аргументы (их флаги) для работы команды,
     * в строке, написанной юзером. 
     * Пример: 
     * <p>
     * register --username name --password pass - сработает
     * <p>
     * login --username name - не сработает
     * @param command - вызываемая команда
     * @param args - мапа ключ+значение, где ключ - флаг аргумента, значение - аргумент
     * @return true - если все аргументы есть, false - если каких-то аргументов не достает
     */
    public boolean validate(String command, Map<String, String> args) {
        if (command == null || command.isBlank()) return false;

        // Команда неизвестна — считаем, что не валидна
        if (!requiredArgs.containsKey(command) && !allowedArgs.containsKey(command)) {
            return false;
        }

        // Нормализуем алиасы и защищаем от null
        Map<String, String> normalized = normalizeAliases(args == null ? Map.of() : args);

        // Проверяем обязательные флаги
        Set<String> required = requiredArgs.getOrDefault(command, Collections.emptySet());
        if (!normalized.keySet().containsAll(required)) {
            return false;
        }

        // Проверяем, нет ли запрещённых флагов (если для команды задан список allowed)
        Set<String> allowed = allowedArgs.getOrDefault(command, Collections.emptySet());
        if (!allowed.isEmpty()) {
            for (String provided : normalized.keySet()) {
                if (!allowed.contains(provided)) return false;
            }
        } else {
            // allowed пустой — значит флаги не ожидаются; если что-то передали — ошибка
            if (!normalized.isEmpty()) return false;
        }

        return true;
    }

    public Map<String, String> normalizeAliases(Map<String, String> args) {
        Map<String, String> normalized = new HashMap<>();

        for (Map.Entry<String, String> entry : args.entrySet()) {

            String originalKey = entry.getKey();
            String normalizedKey = aliases.getOrDefault(originalKey, originalKey);

            normalized.put(normalizedKey, entry.getValue());
        }

        return normalized;
    }

    public Set<String> getRequiredArgs(String command) {
        return requiredArgs.get(command);
    }

    public Set<String> getAllowedArgs(String command) {
        return allowedArgs.get(command);
    }

}
