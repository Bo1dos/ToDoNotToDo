package com.task.manager.infrastructure.event;

import java.util.ResourceBundle;


public class SimpleEvent implements Event {

    private final EventType eventType;
    private final String message;

    // TODO
    // Нужно ResourceBundle где-то передавать по цепочке, и так же по цепочке его исправлять
    public SimpleEvent(EventType eventType, ResourceBundle bundle, Object... args) {
        this.eventType = eventType;
        this.message = eventType.format(bundle, args);
    }

    @Override
    public String message() {
        System.out.println("Запрос сообщения от ивента");
        return message;
    }

    public EventType eventType() {
        return eventType;
    }
    

}
