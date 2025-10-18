package Appointment;

import java.util.Date;

public class Appointment {
    private final String appointmentId;
    private final Date appointmentDate;
    private final String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        // id is required, not blank, and at most 10 characters
        if (appointmentId == null || appointmentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment ID is required.");
        }
        if (appointmentId.length() > 10) {
            throw new IllegalArgumentException("Appointment ID must be 10 characters or fewer.");
        }

        // date is required and cannot be in the past
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date must be in the future.");
        }

        // description is required and at most 50 characters
        if (description == null) {
            throw new IllegalArgumentException("Description is required.");
        }
        if (description.length() > 50) {
            throw new IllegalArgumentException("Description must be 50 characters or fewer.");
        }

        this.appointmentId = appointmentId;
        // defensive copy for mutability safety
        this.appointmentDate = new Date(appointmentDate.getTime());
        this.description = description;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        // return a defensive copy
        return new Date(appointmentDate.getTime());
    }

    public String getDescription() {
        return description;
    }
}
