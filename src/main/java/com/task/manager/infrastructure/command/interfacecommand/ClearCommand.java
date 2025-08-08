package com.task.manager.infrastructure.command.interfacecommand;

import java.io.IOException;

import com.task.manager.infrastructure.command.Command;

public class ClearCommand implements Command<Void> {

    @Override
    public Void execute() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("\033[H\033[2J");
                System.out.flush();
            }
        } catch (InterruptedException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        return null;
    }
    
}
