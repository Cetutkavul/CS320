package com.snhu.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    // ---- constructor & getters ----
    @Test
    void constructor_valid_buildsObject() {
        Task t = new Task("ID123456", "Groceries", "Buy milk and eggs");
        assertEquals("ID123456", t.getId());
        assertEquals("Groceries", t.getName());
        assertEquals("Buy milk and eggs", t.getDescription());
        assertNotNull(t.toString()); // touch toString for coverage
        t.hashCode();                // safe to call
        assertTrue(t.equals(t));     // reflexive
        assertFalse(t.equals(null));
        assertFalse(t.equals("not a task"));
    }

    // ---- id rules ----
    @Test
    void id_required_max10_andImmutable() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null, "N", "D"));
        assertThrows(IllegalArgumentException.class, () -> new Task("ABCDEFGHIJK", "N", "D")); // 11
        Task t = new Task("OKID", "N", "D");
        assertEquals("OKID", t.getId()); // no setter => immutable
    }

    // ---- name rules ----
    @Test
    void name_required_max20_andSettersValidate() {
        assertThrows(IllegalArgumentException.class, () -> new Task("ID1", null, "D"));
        assertThrows(IllegalArgumentException.class, () -> new Task("ID1", rep('X', 21), "D"));

        Task t = new Task("ID1", "Okay", "D");
        t.setName("NewName");
        assertEquals("NewName", t.getName());
        assertThrows(IllegalArgumentException.class, () -> t.setName(null));
        assertThrows(IllegalArgumentException.class, () -> t.setName(rep('Y', 21)));
    }

    // ---- description rules ----
    @Test
    void description_required_max50_andSettersValidate() {
        assertThrows(IllegalArgumentException.class, () -> new Task("ID1", "N", null));
        assertThrows(IllegalArgumentException.class, () -> new Task("ID1", "N", rep('Z', 51)));

        Task t = new Task("ID1", "N", "ok");
        t.setDescription("Updated description");
        assertEquals("Updated description", t.getDescription());
        assertThrows(IllegalArgumentException.class, () -> t.setDescription(null));
        assertThrows(IllegalArgumentException.class, () -> t.setDescription(rep('W', 51)));
    }

    // ---- boundary acceptance (ctor) ----
    @Test
    void boundariesAccepted_inConstructor() {
        String id10   = rep('I', 10);
        String name20 = rep('N', 20);
        String d50    = rep('D', 50);

        Task t = new Task(id10, name20, d50);
        assertEquals(id10, t.getId());
        assertEquals(name20, t.getName());
        assertEquals(d50, t.getDescription());
    }

    // ---- additional setter coverage (no-op then real change) ----
    @Test
    void setters_noOpThenChange_coverPaths() {
        Task t = new Task("S1", "Alpha", "Beta");
        t.setName("Alpha");           // no-op
        t.setDescription("Beta");     // no-op
        t.setName("Gamma");
        t.setDescription("Delta");
        assertEquals("Gamma", t.getName());
        assertEquals("Delta", t.getDescription());
    }

    // ====== NEW TESTS to push +5% coverage ======

    @Test
    void equalsAndHashCode_basedOnIdOnly() {
        Task a = new Task("EQ1", "NameA", "DescA");
        Task b = new Task("EQ1", "DifferentName", "DifferentDesc"); // same id
        Task c = new Task("EQ2", "NameA", "DescA");                  // different id

        // same id -> equals true & hashCode equal
        assertTrue(a.equals(b));
        assertEquals(a.hashCode(), b.hashCode());

        // different id -> not equal
        assertFalse(a.equals(c));
    }

    @Test
    void setters_acceptExactBoundaryLengths() {
        Task t = new Task("BD1", "Start", "StartD");

        String name20 = rep('X', 20);
        String d50    = rep('Y', 50);

        t.setName(name20);
        t.setDescription(d50);

        assertEquals(20, t.getName().length());
        assertEquals(50, t.getDescription().length());
    }

    @Test
    void constructor_allowsEmptyStringsWithinLimits_andToStringShowsFields() {
        // Empty strings are allowed by rubric (non-null, length limits only)
        Task t = new Task("EPTY", "", "");
        assertEquals("EPTY", t.getId());
        assertEquals("", t.getName());
        assertEquals("", t.getDescription());

        // toString should include id and field labels (format may vary; just sanity check substrings)
        String s = t.toString();
        assertTrue(s.contains("EPTY"));
        assertTrue(s.contains("name"));
        assertTrue(s.contains("description"));
    }

    // small helper (avoid String.repeat for Java 8)
    private static String rep(char c, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(c);
        return sb.toString();
    }
}
