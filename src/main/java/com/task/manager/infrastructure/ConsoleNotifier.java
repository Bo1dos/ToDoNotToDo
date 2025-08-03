package com.task.manager.infrastructure;

import com.task.manager.infrastructure.event.Event;

public class ConsoleNotifier implements Notifier {

    @Override
    public void notify(Event event) {
        System.out.println(event.message());
    }
    
}
