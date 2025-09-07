package softmeth.project2;

import util.Date;

/**
 * Represents an appointment between a patient and a healthcare provider.
 * This class encapsulates the details of the appointment, including the date,
 * timeslot, patient, and provider information.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class Appointment implements Comparable <Appointment> {
    protected Date date;
    protected Timeslot timeslot;
    protected Person patient;
    protected Person provider;

    /**
     * Constructor for the Appointment class.
     *
     * @param date The date of the appointment.
     * @param timeslot The timeslot of the appointment.
     * @param patient The patient for the appointment.
     * @param provider The healthcare provider for the appointment.
     */
    public Appointment(Date date, Timeslot timeslot, Person patient, Person provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     * Gets the healthcare provider for this appointment.
     *
     * @return The provider for the appointment.
     */
    public Person getProvider() {
        return this.provider;
    }

    /**
     * Gets the date of the appointment.
     *
     * @return The date of the appointment.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Gets the patient for this appointment.
     *
     * @return The patient for the appointment.
     */
    public Person getPatient() {
        return this.patient;
    }

    /**
     * Gets the timeslot of the appointment.
     *
     * @return The timeslot of the appointment.
     */
    public Timeslot getTimeslot() {
        return this.timeslot;
    }

    /**
     * Creates and returns a Profile object for the patient.
     *
     * @return A Profile object containing the patient's first name, last name, and date of birth.
     */
    public Profile getProfile() {
        return new Profile(patient.getFname(), patient.getLname(), patient.getDob());
    }

    /**
     * Sets a new timeslot for the appointment.
     *
     * @param t The new timeslot to be set.
     * @return The updated timeslot of the appointment.
     */
    public Timeslot setTimeslot(Timeslot t) {
        return this.timeslot = t;
    }

    /**
     * Compares this appointment to another appointment for equality.
     *
     * @param anotherAppointment The appointment to compare to.
     * @return true if the appointments are considered equal, false otherwise.
     */
    @Override
    public boolean equals(Object anotherAppointment) {
        if (this == anotherAppointment) {
            return true;
        }
        if (!(anotherAppointment instanceof Appointment newAppointment)) {
            return false;
        }
        return (date.equals(newAppointment.date)) &&
                (timeslot == newAppointment.timeslot) &&
                (patient.equals(newAppointment.patient)) &&
                (provider.equals(newAppointment.provider));
    }

    /**
     * Returns a string representation of the appointment.
     *
     * @return A formatted string containing the appointment details.
     */
    @Override
    public String toString() {
        return "softmeth.project2.Appointment{" +
                "util.Date ='" + date + '\'' +
                ", softmeth.project2.Timeslot ='" + timeslot + '\'' +
                ", softmeth.project2.Patient =" + patient + '\'' +
                ", softmeth.project2.Provider =" + provider +
                '}';
    }

    /**
     * Compares this appointment to another appointment for ordering.
     * The comparison is done based on the patient's last name, first name,
     * date of birth, appointment date, and timeslot.
     *
     * @param appt The appointment to compare with.
     * @return A negative integer, zero, or a positive integer as this appointment
     * is less than, equal to, or greater than the specified appointment.
     */
    @Override
    public int compareTo(Appointment appt) {
        // Compare by last name
        int lastNameComparison = this.patient.getLname().compareTo(appt.patient.getLname());
        // If last names are equal, compare by first name
        if (lastNameComparison == 0) {
            int firstNameComparison = this.patient.getFname().compareTo(appt.patient.getFname());
            // If first names are equal, compare by date of birth
            if (firstNameComparison == 0) {
                int dobComparison = this.patient.getDob().compareTo(appt.patient.getDob());
                // If date of birth is also equal, compare by appointment date and time
                if (dobComparison == 0) {
                    int dateComparison = this.date.compareTo(appt.date);

                    // If dates are equal, compare by timeslot
                    if (dateComparison == 0) {
                        return this.timeslot.compareTo(appt.timeslot);
                    }
                    return dateComparison;
                }
                return dobComparison;
            }
            return firstNameComparison;
        }
        return lastNameComparison;
    }

    /**
     * Compares this appointment to another appointment by the provider's location,
     * appointment date, and timeslot.
     *
     * @param appointment The appointment to compare with.
     * @return A negative integer, zero, or a positive integer as this appointment
     * is less than, equal to, or greater than the specified appointment by location.
     */
    public int compareByLocation(Appointment appointment) {
        int countyComparison = this.provider.getLocation().getCounty().compareTo(appointment.provider.getLocation().getCounty());

        if (countyComparison != 0) {
            return countyComparison;
        }

        int dateComparison = this.date.compareTo(appointment.date);

        if (dateComparison != 0) {
            return dateComparison;
        }
        return this.timeslot.compareTo(appointment.timeslot);
    }

    /**
     * Compares this appointment to another appointment by appointment date,
     * timeslot, and provider's last name.
     *
     * @param appointment The appointment to compare with.
     * @return A negative integer, zero, or a positive integer as this appointment
     * is less than, equal to, or greater than the specified appointment by appointment date.
     */
    public int compareByAppointment(Appointment appointment) {
        int dateComparison = this.date.compareTo(appointment.date);

        if (dateComparison != 0) {
            return dateComparison;
        }

        int timeslotComparison = this.getTimeslot().compareTo(appointment.getTimeslot());

        if (timeslotComparison != 0) {
            return timeslotComparison;
        }
        return this.getProvider().getLname().compareTo(appointment.getProvider().getLname());
    }
}