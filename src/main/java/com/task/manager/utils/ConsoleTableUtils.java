package com.task.manager.utils;

import java.util.List;
import java.util.ResourceBundle;

import com.task.manager.domain.dto.TaskDTO;

public class ConsoleTableUtils {

    public static void printTasks(List<TaskDTO> tasks) {
        System.out.printf("%-36s %-20s %-10s %-30s %-30s\n",
            "ID", "Header", "Status", "Deadline", "CreatedAt");
        System.out.println("=".repeat(130));
        for (TaskDTO task : tasks) {
            System.out.printf("%-36s %-20s %-10s %-30s %-30s\n",
                task.getId(),
                task.getHeader(),
                task.getTaskStatus(),
                task.getDeadLine() != null ? task.getDeadLine() : "-",
                task.getCreationAt());
        }
    }

    public static void printTask(TaskDTO task) {
        System.out.println("=".repeat(55));
        System.out.printf("%-12s: %s%n", "ID", task.getId());
        System.out.printf("%-12s: %s%n", "Header", task.getHeader());
        System.out.printf("%-12s: %s%n", "Desc", task.getDescription());
        System.out.printf("%-12s: %s%n", "Status", task.getTaskStatus());
        System.out.printf("%-12s: %s%n", "Deadline", 
            task.getDeadLine() != null ? task.getDeadLine() : "-");
        System.out.printf("%-12s: %s%n", "CreatedAt", task.getCreationAt());
        System.out.println("=".repeat(55));
    }

    public static void printInfo(String msg) {
        System.out.println("\u001B[32m[INFO]\u001B[0m " + msg);
    }

    public static void printError(String msg) {
        System.out.println("\u001B[31m[ERROR]\u001B[0m " + msg);
    }

    public static void printWarning(String msg) {
        System.out.println("\u001B[33m[WARN]\u001B[0m " + msg);
    }

    public static void printHelpMessage(ResourceBundle bundle) {
        System.out.println(bundle.getString("help.title"));
        System.out.println("──────────────────────────────────────────────");
        System.out.println(bundle.getString("help.commands"));
        System.out.println("──────────────────────────────────────────────");
    }
}

