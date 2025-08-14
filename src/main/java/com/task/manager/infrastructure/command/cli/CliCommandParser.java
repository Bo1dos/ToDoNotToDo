package com.task.manager.infrastructure.command.cli;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CliCommandParser {


    public Map<String, String> parseArgs(String input) {

        Map<String, String> args = new HashMap<>();

        String [] tokens = input.trim().split(" (?=--)");

        if (tokens.length == 1) {
            return args;
        }


        for (String token : tokens) {

            if (!token.startsWith("--")) { // нет префикса - это команда
                continue;
            }

            token = token.substring(2);

            // Разбиваем токен вида: flag arg на flag и arg
            int spaceInd = token.indexOf(' ');
            String key = token.substring(0, spaceInd);

            if (spaceInd == -1) {
               args.put(key, "");
            } else {
                
                String value = token.substring(spaceInd+1, token.length());
                

                if(value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length()-1);
                }

                args.put(key, value);
            }
        }
        

        return args;
    }

    public String parseNameCommand(String input) {
        int ind = input.indexOf(' ');

        // Команда без флагов\аргументов
        if (ind == -1) {
            ind = input.length();
        }

        String command = input.substring(0, ind);

        return command;
    }
}
