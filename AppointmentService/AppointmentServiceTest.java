package Appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {
    private AppointmentService service;
    private Appointment appointment;

    @BeforeEach
    public void setUp() {
        service = new AppointmentService();
        Date futureDate = new Date(System.currentTimeMillis() + 86_400_000L); // 1 day
        appointment = new Appointment("A1001", futureDate, "Eye Exam");
    }

    @Test
    public void testAddAppointment() {
        service.addAppointment(appointment);
        Appointment fromService = service.getAppointment("A1001");
        assertNotNull(fromService);
        assertEquals(appointment.getAppointmentId(), fromService.getAppointmentId());
        assertEquals(appointment.getAppointmentDate(), fromService.getAppointmentDate());
        assertEquals(appointment.getDescription(), fromService.getDescription());
    }

    @Test
    public void testAddDuplicateAppointment() {
        service.addAppointment(appointment);
        assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment(new Appointment("A1001",
                new Date(System.currentTimeMillis() + 172_800_000L),
                "Follow Up"));
        });
    }

    @Test
    public void testDeleteAppointment() {
        service.addAppointment(appointment);
        service.deleteAppointment("A1001");
        assertNull(service.getAppointment("A1001"));
    }

    @Test
    public void testDeleteNonExistentAppointment() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteAppointment("B9999");
        });
    }

    @Test
    public void testAddTwoAppointmentsAndRetrieve() {
        service.addAppointment(appointment);
        Appointment appt2 = new Appointment("A2002",
            new Date(System.currentTimeMillis() + 259_200_000L),
            "Physical Therapy");

        service.addAppointment(appt2);

        assertNotNull(service.getAppointment("A1001"));
        assertNotNull(service.getAppointment("A2002"));
        assertNull(service.getAppointment("NOPE"));
    }
}
