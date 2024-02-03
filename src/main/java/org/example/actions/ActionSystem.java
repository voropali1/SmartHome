package org.example.actions;

import java.util.LinkedList;
import java.util.Queue;

public class ActionSystem {
    private final Queue<Action> tasks = new LinkedList<>();

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