package com.task.manager.infrastructure;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.AllArgsConstructor;

public class MessageBundleProvider {
    private Locale currentLocale = Locale.getDefault(); // по умолчанию

    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle("messages", currentLocale);
    }

    public void setLocale(Locale newLocale) {
        this.currentLocale = newLocale;
        ResourceBundle.clearCache(); // на всякий случай
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}

