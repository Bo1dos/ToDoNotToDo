package com.task.manager.infrastructure.notification;

import com.task.manager.infrastructure.Notifier;
import com.task.manager.infrastructure.event.Event;

public class DefaultNotificationService implements NotificationService {
    
    private final Notifier notifier;

    public DefaultNotificationService(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void publish(Event event) {
        notifier.notify(event);
    }
}

