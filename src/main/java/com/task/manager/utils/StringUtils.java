package com.task.manager.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StringUtils {
    public static Instant toInstant(String input) {
        if (input == null || input.isBlank()) return null;

        // 1) Чистый ISO_INSTANT: 2025-08-13T21:00:00Z
        try {
            return Instant.parse(input);
        } catch (DateTimeParseException ignored) { }

        // 2) OffsetDateTime: 2025-08-13T23:00:00+03:00
        try {
            return OffsetDateTime.parse(input).toInstant();
        } catch (DateTimeParseException ignored) { }

        // 3) LocalDateTime with optional separators: try patterns
        DateTimeFormatter [] patterns = new DateTimeFormatter[] {
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,                              // 2025-08-13T23:00
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),         // 2025-08-13 23:00:00
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),            // 2025-08-13 23:00
            DateTimeFormatter.ISO_LOCAL_DATE                                    // 2025-08-13
        };

        for (DateTimeFormatter dateTimeFormatter : patterns) {
            try {
                if (dateTimeFormatter == DateTimeFormatter.ISO_LOCAL_DATE){
                    LocalDate localDate = LocalDate.parse(input);
                    return localDate
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant();

                } else {
                    LocalDateTime localDateTime = LocalDateTime.parse(input);
                    return localDateTime
                                .atZone(ZoneId.systemDefault())
                                .toInstant();
                }
            } catch (DateTimeParseException ignored) { }
        }

        // 4) Epoch millis
        try {
            Long epoch = Long.parseLong(input);
            return Instant.ofEpochMilli(epoch);

        } catch (DateTimeParseException ignored) { }

        // couldn't parse
         throw new IllegalArgumentException("Unsupported date/time format: " + input +
            ". Supported: ISO-8601, yyyy-MM-dd, yyyy-MM-dd HH:mm, yyyy-MM-dd HH:mm:ss, epoch millis.");
    }
}

