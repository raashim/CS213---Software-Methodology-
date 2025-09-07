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

    @Override
    public boolean equals(Object anotherAppointment){
        if(this==anotherAppointment){
            return true;
        }
        if(!(anotherAppointment instanceof Appointment newAppointment)){
            return false;
        }
        return  (date.equals(newAppointment.date)) &&
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


    @Override
    public int compareTo(Appointment appointment) {
        int check = this.patient.compareTo(appointment.patient);
        if (check != 0) { //if the patients are different
            return check;
        }

        if ((this.date.compareTo(appointment.date)) != 0) { //if the dates are different
            check = this.date.compareTo(appointment.date);
            return check;
        }


        if ((this.timeslot.compareTo(appointment.timeslot)) != 0) { //if the timeslots are different
            check = this.timeslot.compareTo(appointment.timeslot);
            return check;
        }

        return check;
    }

    public int compareByLocation(Appointment appointment) {
        //ordered by county, date, and then timeslot
        int check = this.provider.getLocation().getCounty().compareTo(appointment.provider.getLocation().getCounty());

        if (check != 0) { //if the counties are different
            return check;
        }

        if ((this.date.compareTo(appointment.date) != 0)) { //if the dates are different
            check = this.date.compareTo(appointment.date);
            return check;
        }

        if((this.timeslot.compareTo(appointment.timeslot) != 0)) { //if the timeslots are different
            check = this.timeslot.compareTo(appointment.timeslot);
            return check;
        }

        return check;
    }

    public int compareByAppointment(Appointment appointment) {
        //ordered by date, then timeslot, and then provider name
        int check = this.date.compareTo(appointment.date);

        if(check != 0) { //if the dates are different
            return check;
        }

        if((this.timeslot.compareTo(appointment.timeslot) != 0)) { //if the timeslots are different
            check = this.timeslot.compareTo(appointment.timeslot);
            return check;
        }

        if((this.provider.compareTo(appointment.provider) != 0)) { //if the providers are different
            check = this.provider.compareTo(appointment.provider);
            return check;
        }

        return check;
    }
}