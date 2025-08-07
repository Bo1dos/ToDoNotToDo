package com.task.manager.infrastructure;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.AllArgsConstructor;

public class MessageBundleProvider {
    private Locale currentLocale = Locale.getDefault(); // по умолчанию
    private ResourceBundle bundle = ResourceBundle.getBundle("messages", currentLocale);

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setLocale(Locale newLocale) {
        this.currentLocale = newLocale;
        this.bundle = ResourceBundle.getBundle("messages", currentLocale);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}

