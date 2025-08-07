package com.task.manager.infrastructure.command.interfacecommand;

import java.util.Locale;

import com.task.manager.infrastructure.MessageBundleProvider;
import com.task.manager.infrastructure.command.Command;

public class SetLocaleCommand implements Command<Void> {
    private final MessageBundleProvider bundleProvider;
    private final String langCode;

    public SetLocaleCommand(MessageBundleProvider provider, String langCode) {
        this.bundleProvider = provider;
        this.langCode = langCode;
    }

    @Override
    public Void execute() {
        bundleProvider.setLocale(Locale.forLanguageTag(langCode));
        System.out.println("Язык установлен: " + langCode);
        return null;
    }
}

