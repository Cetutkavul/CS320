package com.snhu.tasks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory Task service (no DB, no UI).
 *
 * Requirements:
 * - addTask: add tasks with a UNIQUE id; returns true if added, false if duplicate.
 *   Validates Task fields (constructor throws for invalid).
 * - deleteTask: delete by id; returns true if removed, false if id missing or null.
 * - updateName / updateDescription: update those fields for a given id; return true if updated,
 *   false if id missing or null; propagate IllegalArgumentException for invalid new values.
 * - getTask: convenience for tests (returns a live reference to stored Task).
 */
public class TaskService {
    private final Map<String, Task> tasks = new HashMap<>();

    /** Adds a task with unique id. Throws if fields invalid; returns false on duplicate id. */
    public boolean addTask(String id, String name, String description) {
        // Validate by constructing first; this matches rubric (constructor enforces constraints).
        Task candidate = new Task(id, name, description);
        synchronized (tasks) {
            if (tasks.containsKey(candidate.getId())) {
                return false; // duplicate id
            }
            tasks.put(candidate.getId(), candidate);
            return true;
        }
    }

    /** Deletes a task by id. Returns true if it existed, false otherwise (including null id). */
    public boolean deleteTask(String id) {
        if (id == null) return false;
        synchronized (tasks) {
            return tasks.remove(id) != null;
        }
    }

    /**
     * Updates the name of a task by id.
     * Returns true if id exists and update applied; false if id does not exist or is null.
     * Throws IllegalArgumentException if newName is invalid per Task rules.
     */
    public boolean updateName(String id, String newName) {
        if (id == null) return false;
        synchronized (tasks) {
            Task t = tasks.get(id);
            if (t == null) return false;
            // validate via setter (may throw IllegalArgumentException)
            t.setName(newName);
            return true;
        }
    }

    /**
     * Updates the description of a task by id.
     * Returns true if id exists and update applied; false if id does not exist or is null.
     * Throws IllegalArgumentException if newDescription is invalid per Task rules.
     */
    public boolean updateDescription(String id, String newDescription) {
        if (id == null) return false;
        synchronized (tasks) {
            Task t = tasks.get(id);
            if (t == null) return false;
            t.setDescription(newDescription); // validates
            return true;
        }
    }

    /** Returns the task for the id, or null if not found (used by tests). */
    public Task getTask(String id) {
        if (id == null) return null;
        synchronized (tasks) {
            return tasks.get(id);
        }
    }

    /** Exposes a read-only snapshot of all tasks (handy for debugging). */
    public Map<String, Task> snapshot() {
        synchronized (tasks) {
            return Collections.unmodifiableMap(new HashMap<>(tasks));
        }
    }

    /** Clear all tasks (useful for isolated testing). */
    public void clear() {
        synchronized (tasks) {
            tasks.clear();
        }
    }
}
