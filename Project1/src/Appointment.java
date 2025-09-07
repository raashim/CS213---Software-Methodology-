public class Appointment implements Comparable<Appointment> {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public Date getDate() {
        return this.date;
    }

    public Profile getPatient() {
        return this.patient;
    }

    public Timeslot getTimeslot() {
        return this.timeslot;
    }

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

    @Override
    public String toString() {
        return "Appointment{" +
                "Date ='" + date + '\'' +
                ", Timeslot ='" + timeslot + '\'' +
                ", Patient =" + patient + '\'' +
                ", Provider =" + provider +
                '}';
    }

    @Override //this doesn't
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

    public int compareByLocation(Appointment appointment) {
        if(appointment.getProvider() == null) {
            return -1;
        }

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

    public int compareByAppointment(Appointment appointment) {
        int dateComparison = this.date.compareTo(appointment.date);

        if (dateComparison != 0) {
            return dateComparison;
        }

        int timeslotComparison = this.timeslot.compareTo(appointment.timeslot);

        if (timeslotComparison != 0) {
            return timeslotComparison;
        }
        return this.provider.compareTo(appointment.provider);
    }
}