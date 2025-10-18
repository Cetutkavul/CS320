package Appointment;

import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
    private final Map<String, Appointment> store = new HashMap<>();

    public void addAppointment(Appointment appt) {
        if (appt == null) {
            throw new IllegalArgumentException("Appointment is required.");
        }
        String id = appt.getAppointmentId();
        if (store.containsKey(id)) {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }
        store.put(id, appt);
    }

    public void deleteAppointment(String appointmentId) {
        if (appointmentId == null || !store.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID not found.");
        }
        store.remove(appointmentId);
    }

    public Appointment getAppointment(String appointmentId) {
        return store.get(appointmentId);
    }
}
