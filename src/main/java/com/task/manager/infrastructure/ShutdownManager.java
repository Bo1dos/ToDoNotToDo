package com.task.manager.infrastructure;

import java.util.concurrent.atomic.AtomicBoolean;

public class ShutdownManager {
    
    private final AtomicBoolean running = new AtomicBoolean(true);

    public boolean isRunning() { 
        return running.get(); 
    }

    public void requestShutdown() { 
        running.set(false); 
    }
}

