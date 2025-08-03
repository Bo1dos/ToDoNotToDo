package com.task.manager.infrastructure;

import com.task.manager.infrastructure.event.Event;

/**
 * Интерфейс для уведомления пользователя о том или ином событии
 */
public interface Notifier {
    void notify(Event event);
}
