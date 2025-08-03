package com.task.manager.infrastructure.notification;

import com.task.manager.infrastructure.event.Event;

public interface NotificationService {

    void publish(Event event);
    
}

