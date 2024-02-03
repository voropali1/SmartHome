package org.example.actions;

import org.example.events.*;

import java.util.LinkedList;
import java.util.Queue;

public class Action {
    private  Queue<Action> tasks = new LinkedList<>();
    private final Event event;

    /**
     * Instantiates a new Task.
     *
     * @param event the event
     *
     */
   public Action(Event event) {
       this.event = event;
   }




    /**
     * Add task to the queue.
     * @param task task to add
     */
    public void addTask(Action task) {
        tasks.add(task);
    }

    /**
     * Returns task from the queue.
     * @return task
     */
    public Action getTask() {
        return tasks.poll();
    }

    /**
     * Checks if queue is empty.
     * @return true if empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
