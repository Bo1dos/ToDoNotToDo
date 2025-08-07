package com.task.manager.utils;

import java.util.List;

import com.task.manager.domain.dto.TaskDTO;

public class ConsoleTableUtils {
    public static void printTasks(List<TaskDTO> tasks) {
        System.out.printf("%-36s %-20s %-10s\n", "ID", "Header", "Status");
        System.out.println("=".repeat(70));
        for (TaskDTO task : tasks) {
            System.out.printf("%-36s %-20s %-10s\n",
                task.getId(), task.getHeader(), task.getTaskStatus());
        }
    }

    public static void printMessage(String msg) {
        System.out.println("[INFO] " + msg);
    }

    public static void printHelpMessage() {
        System.out.println("📖 Доступные команды:"); //TODO: сделать под локализацию
        System.out.println("──────────────────────────────────────────────");
        System.out.println("register --username <u> --password <p>");
        System.out.println("login --username <u> --password <p>");
        System.out.println("logout");
        System.out.println();
        System.out.println("create-task [--header <h>] [--desc <d>] [--deadline <dl>]");
        System.out.println("update-task --id <uuid> [--header <h>] [--desc <d>] [--deadline <dl>]");
        System.out.println("delete-task --id <uuid>");
        System.out.println("show-task --id <uuid>");
        System.out.println("find-task-by-header --header <h>");
        System.out.println();
        System.out.println("list-tasks | list-done | list-undone | list-overdue");
        System.out.println();
        System.out.println("set-language --lang <en|ru>");
        System.out.println("help");
        System.out.println("──────────────────────────────────────────────");
    }
}

