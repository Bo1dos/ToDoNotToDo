package com.task.manager.infrastructure.command.cli;

import java.util.HashMap;
import java.util.Map;


public class CliCommandParser {


    public Map<String, String> parseArgs(String input) {

        Map<String, String> args = new HashMap<>();

        String [] tokens = input.trim().split(" (?=--)");

        for (String token : tokens) {
            token = token.substring(2);

            // Разбиваем токен вида: flag arg на flag и arg
            int spaceInd = token.indexOf(' ');
            String key = token.substring(0, spaceInd);

            if (spaceInd != -1) {
               args.put(key, "");
            } else {
                
                String value = token.substring(spaceInd+1, token.length());
                

                if(value.startsWith("\"") && value.endsWith("\"")) {
                    value.substring(1, value.length()-1);
                }

                args.put(key, value);
            }
        }
        

        return args;
    }
}
