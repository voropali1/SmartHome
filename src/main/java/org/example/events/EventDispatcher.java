package org.example.events;

import org.example.SmartHome.SmartHome;

import java.io.IOException;
import java.util.*;
public class EventDispatcher {
    private final Map<String, LinkedList<EventHandler>> eventHandlers = new HashMap<>();
    public void addEventHandler(Class<? extends Event> event, String context, EventHandler handler) {
        String key = event + context;
        if (eventHandlers.containsKey(key)) {
            handler.setNext(eventHandlers.get(key).getLast());
            eventHandlers.get(key).add(handler);
        } else {
            LinkedList<EventHandler> handlers = new LinkedList<>(Collections.singletonList(handler));
            eventHandlers.put(key, handlers);
        }
    }
    public void removeEventHandler(Class<? extends Event> event, String context, EventHandler handler) {
        String key = event + context;

        if (eventHandlers.containsKey(key)) {
            eventHandlers.get(key).remove(handler);

            if (eventHandlers.get(key).size() == 0) {
                eventHandlers.remove(key);
            }
        }
    }
    public void dispatchEvent(Event e, String context) {
        String key = e.getClass() + context;
        try {
            SmartHome.getInstance().getReportSystem().getEventReport().generateReport(e);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        if (eventHandlers.containsKey(key)) {
            Objects.requireNonNull(eventHandlers.get(key).peekLast()).handle(e);
        }
    }
}
