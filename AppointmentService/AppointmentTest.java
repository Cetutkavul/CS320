package Appointment;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    @Test
    public void testValidAppointment() {
        Date futureDate = new Date(System.currentTimeMillis() + 43_200_000L); // 12 hours
        Appointment appt = new Appointment("B2025", futureDate, "Annual Physical");
        assertEquals("B2025", appt.getAppointmentId());
        assertEquals(futureDate, appt.getAppointmentDate());
        assertEquals("Annual Physical", appt.getDescription());
    }

    @Test
    public void testInvalidIdEmptyString() {
        Date futureDate = new Date(System.currentTimeMillis() + 43_200_000L);
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("", futureDate, "Routine Check");
        });
    }

    @Test
    public void testIdTooLong() {
        Date futureDate = new Date(System.currentTimeMillis() + 86_400_000L);
        String longId = "ABCDEFGHIJK"; // 11 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(longId, futureDate, "Checkup");
        });
    }

    @Test
    public void testPastDate() {
        Date pastDate = new Date(System.currentTimeMillis() - 3_600_000L); // 1 hour ago
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("C3030", pastDate, "Follow Up Visit");
        });
    }

    @Test
    public void testNullDescription() {
        Date futureDate = new Date(System.currentTimeMillis() + 172_800_000L); // 2 days
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("C4040", futureDate, null);
        });
    }

    @Test
    public void testDescriptionTooLong() {
        Date futureDate = new Date(System.currentTimeMillis() + 259_200_000L); // 3 days
        String tooLong = "This description contains more than fifty characters to trigger validation";
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("C5050", futureDate, tooLong);
        });
    }
}
