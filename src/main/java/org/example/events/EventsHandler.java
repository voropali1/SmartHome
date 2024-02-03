package org.example.events;

public abstract class EventsHandler implements EventHandler {
    protected EventHandler nextHandler;

    @Override
    public void setNext(EventHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
