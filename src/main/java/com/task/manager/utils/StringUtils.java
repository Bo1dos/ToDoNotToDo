package com.task.manager.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class StringUtils {
    public static Instant toInstant(String input) {
        if (input == null || input.isBlank()) return null;
        return LocalDate.parse(input).atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
}

