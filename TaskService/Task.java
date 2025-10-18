package com.snhu.tasks;

import java.util.Objects;

/**
 * Task domain object.
 *
 * Requirements:
 * - id: required, unique (enforced by service), non-null, max 10 chars, immutable
 * - name: required, non-null, max 20 chars, updatable with validation
 * - description: required, non-null, max 50 chars, updatable with validation
 */
public final class Task {

    private final String id;          // immutable
    private String name;              // updatable
    private String description;       // updatable

    // ---- constants used by tests and validation ----
    public static final int MAX_ID_LEN = 10;
    public static final int MAX_NAME_LEN = 20;
    public static final int MAX_DESC_LEN = 50;

    public Task(String id, String name, String description) {
        this.id = requireNonNullAndMax("id", id, MAX_ID_LEN);
        setName(name);                // reuse validation
        setDescription(description);  // reuse validation
    }

    // ---------- getters ----------
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    // ---------- updaters with validation ----------
    public void setName(String name) {
        this.name = requireNonNullAndMax("name", name, MAX_NAME_LEN);
    }

    public void setDescription(String description) {
        this.description = requireNonNullAndMax("description", description, MAX_DESC_LEN);
    }

    // ---------- basic helpers (increase coverage, helpful in logs) ----------
    @Override
    public String toString() {
        return "Task{id='" + id + "', name='" + name + "', description='" + description + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task other = (Task) o;
        // Identity by id only (id is immutable & unique in service)
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ---------- validation utility ----------
    private static String requireNonNullAndMax(String field, String value, int max) {
        if (value == null) {
            throw new IllegalArgumentException(field + " must not be null");
        }
        if (value.length() > max) {
            throw new IllegalArgumentException(field + " length must be <= " + max);
        }
        return value;
    }
}
