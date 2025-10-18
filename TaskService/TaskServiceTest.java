package com.snhu.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void setup() {
        service = new TaskService();
    }

    // ---- add + get ----
    @Test
    void addTask_uniqueId_succeeds_andGetReturnsTask() {
        assertTrue(service.addTask("T001", "Call Mom", "Sunday afternoon"));
        Task got = service.getTask("T001");
        assertNotNull(got);
        assertEquals("Call Mom", got.getName());
        assertEquals("Sunday afternoon", got.getDescription());
    }

    @Test
    void addTask_duplicateId_fails_andOriginalRemains() {
        assertTrue(service.addTask("T001", "One", "Desc"));
        assertFalse(service.addTask("T001", "Two", "Desc2"));
        assertEquals("One", service.getTask("T001").getName());
    }

    // ---- delete ----
    @Test
    void deleteTask_removesTask_andIsIdempotent() {
        assertTrue(service.addTask("T002", "Task", "Desc"));
        assertTrue(service.deleteTask("T002"));
        assertNull(service.getTask("T002"));
        assertFalse(service.deleteTask("T002")); // deleting again -> false
    }

    @Test
    void deleteTask_withNullOrMissingId_returnsFalse() {
        assertFalse(service.deleteTask(null));
        assertFalse(service.deleteTask("NOT_THERE"));
    }

    // ---- updates ----
    @Test
    void updateName_validatesAndUpdates_byId() {
        assertTrue(service.addTask("T003", "Old", "Desc"));

        assertTrue(service.updateName("T003", "New"));
        assertEquals("New", service.getTask("T003").getName());

        assertThrows(IllegalArgumentException.class, () -> service.updateName("T003", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateName("T003", rep('A', 21)));
        assertFalse(service.updateName("MISSING", "X"));
    }

    @Test
    void updateDescription_validatesAndUpdates_byId() {
        assertTrue(service.addTask("T004", "Name", "Old"));

        assertTrue(service.updateDescription("T004", "New description"));
        assertEquals("New description", service.getTask("T004").getDescription());

        assertThrows(IllegalArgumentException.class, () -> service.updateDescription("T004", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateDescription("T004", rep('B', 51)));
        assertFalse(service.updateDescription("MISSING", "X"));
    }

    @Test
    void update_withNullId_returnsFalse() {
        assertFalse(service.updateName(null, "X"));
        assertFalse(service.updateDescription(null, "Y"));
    }

    // ---- constructor-level validation exercised via addTask ----
    @Test
    void addTask_rejectsInvalidFields_viaConstructorValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addTask("T005", "ABCDEFGHIJKLMNOPQRSTU", "ok")); // name > 20
        assertThrows(IllegalArgumentException.class,
                () -> service.addTask("T006", "ok", rep('z', 51)));           // desc > 50
        assertThrows(IllegalArgumentException.class,
                () -> service.addTask("01234567890", "ok", "ok"));            // id > 10
        assertThrows(IllegalArgumentException.class,
                () -> service.addTask(null, "ok", "ok"));                     // id null
    }

    // ---- boundaries & sequences to hit true/false branches ----
    @Test
    void boundaryAdd_updateThenCheck_persistsValues() {
        String id10   = rep('I', 10);
        String name20 = rep('N', 20);
        String d50    = rep('D', 50);

        assertTrue(service.addTask(id10, name20, d50));
        String name20b = rep('A', 20);
        String d50b    = rep('B', 50);
        assertTrue(service.updateName(id10, name20b));
        assertTrue(service.updateDescription(id10, d50b));

        Task t = service.getTask(id10);
        assertNotNull(t);
        assertEquals(name20b, t.getName());
        assertEquals(d50b, t.getDescription());
    }

    @Test
    void addUpdateDelete_manyTasks_exercisesTrueAndFalsePaths() {
        assertTrue(service.addTask("A1", "N1", "D1"));
        assertTrue(service.addTask("A2", "N2", "D2"));
        assertTrue(service.addTask("A3", "N3", "D3"));

        assertFalse(service.addTask("A2", "N2b", "D2b")); // duplicate id

        assertTrue(service.updateName("A1", "N1x"));
        assertTrue(service.updateDescription("A1", "D1x"));
        assertFalse(service.updateName("NOPE", "Z"));
        assertFalse(service.updateDescription("NOPE", "Z"));

        Task t2 = service.getTask("A2");
        assertNotNull(t2);
        assertEquals("N2", t2.getName());
        assertEquals("D2", t2.getDescription());

        assertTrue(service.deleteTask("A3"));
        assertFalse(service.deleteTask("A3"));

        assertNotNull(service.getTask("A1"));
        assertNotNull(service.getTask("A2"));
        assertNull(service.getTask("A3"));
    }

    // ---- snapshot & clear (extra lines) ----
    @Test
    void snapshot_and_clear_work() {
        assertTrue(service.addTask("S1", "N", "D"));
        Map<String, Task> snap = service.snapshot();
        assertTrue(snap.containsKey("S1"));
        // snapshot is read-only
        assertThrows(UnsupportedOperationException.class, () -> snap.clear());

        service.clear();
        assertNull(service.getTask("S1"));
        assertTrue(service.snapshot().isEmpty());
    }

    // small helper (avoid String.repeat for Java 8)
    private static String rep(char c, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(c);
        return sb.toString();
    }
}
