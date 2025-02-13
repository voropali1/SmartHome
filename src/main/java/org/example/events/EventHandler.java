package org.example.events;

public interface EventHandler {

    void setNext(EventHandler handler);


    void handle(Event e);
}