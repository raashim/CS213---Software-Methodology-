import java.text.DecimalFormat;

/**
 * This class represents a list of appointments. It provides methods to add,
 * remove, and manipulate the list, such as sorting and printing appointments by
 * patient, location, or appointment details.
 */
public class List {
    private Appointment[] appointments;
    private int size; //number of appointments in the array

    /**
     * Constructs an empty List with an initial capacity of 4 appointments.
     */
    public List() {
        size = 0;
        appointments = new Appointment[4];
    }

    /**
     * Retrieves the number of appointments in the list.
     *
     * @return the current size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Retrieves an appointment at the specified index.
     *
     * @param index the index of the appointment
     * @return the appointment at the specified index
     */
    public Appointment getAppointment(int index) {
        return this.appointments[index];
    }

    /**
     * Checks if the list contains the specified appointment.
     *
     * @param appointment the appointment to search for
     * @return true if the appointment exists in the list, false otherwise
     */
    public boolean contains(Appointment appointment) {
        int num = find(appointment);
        if (num != -1) {
            return true;
        }
        return false;
    }

    /**
     * Adds an appointment to the list if it doesn't already exist.
     * Expands the internal array if needed.
     *
     * @param appointment the appointment to add
     */
    public void add(Appointment appointment) {
        if (!contains(appointment)) {

            int n = appointments.length - 1;
            if (size == n) {
                grow();
            }
            appointments[size++] = appointment;
        }
    }

    /**
     * Removes the specified appointment from the list.
     *
     * @param appointment the appointment to remove
     */
    public void remove(Appointment appointment) {
        if (contains(appointment)) {
            int indexOfApp = find(appointment);
            for (int i = indexOfApp; i < size - 1; i++) {
                appointments[i] = appointments[i + 1];
            }
            appointments[--size] = null;
        }
    }

    /**
     * Expands the internal array by 4 additional spaces when needed.
     */
    private void grow() {
        int size = appointments.length;

        Appointment[] newAppt = new Appointment[size + 4];
        for (int i = 0; i < size; i++) {
            newAppt[i] = appointments[i];
        }
        appointments = newAppt; //stores new array into old array
    }

    /**
     * Finds the index of the specified appointment in the list.
     *
     * @param appointment the appointment to search for
     * @return the index of the appointment or -1 if not found
     */
    private int find(Appointment appointment) {//helper method
        int NOT_FOUND = -1;
        for (int i = 0; i < appointments.length; i++) {
            if (appointments[i] != null && appointments[i].compareTo(appointment) == 0) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Sorts and prints the list of appointments by patient, then date, and timeslot.
     */
    public void printByPatient() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].compareTo(appointments[j + 1]) > 0) {

                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        System.out.println("** Appointments ordered by patient/date/time **");
        for (int i = 0; i < size; i++) {
            String date = formatDate(appointments[i]);
            String timeslot = formatTimeslot(appointments[i].getTimeslot());
            String fname = appointments[i].getPatient().getFname();
            String lname = appointments[i].getPatient().getLname();
            String name = fname + " " + lname;
            Date dob = appointments[i].getPatient().getDob();
            String dobFormat = String.format("%d/%d/%d", dob.getMonth(), dob.getDay(), dob.getYear());
            Provider provider = appointments[i].getProvider();
            String providerDetails = String.format("[%s, %s, %s %s, %s]", provider.name(), provider.getLocation().name().toUpperCase(), provider.getLocation().getCounty(), provider.getLocation().getZip(), provider.getSpecialty().toString().toUpperCase());
            String format = String.format("%s %s %s %s %s", date, timeslot, name, dobFormat, providerDetails);
            System.out.println(format);
        }
        System.out.println("** end of list **");
    }

    /**
     * Sorts and prints the list of appointments by location, then date, and timeslot.
     */
    public void printByLocation() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].compareByLocation(appointments[j + 1]) > 0) {
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        System.out.println("** Appointments ordered by county/date/time **");
        for (int i = 0; i < size; i++) {
            String date = formatDate(appointments[i]);
            String timeslot = formatTimeslot(appointments[i].getTimeslot());
            String fname = appointments[i].getPatient().getFname();
            String lname = appointments[i].getPatient().getLname();
            String name = fname + " " + lname;
            Date dob = appointments[i].getPatient().getDob();
            String dobFormat = String.format("%d/%d/%d", dob.getMonth(), dob.getDay(), dob.getYear());
            Provider provider = appointments[i].getProvider();

            if(provider == null) {
                provider = provider.PATEL;
            }
            String providerDetails = String.format("[%s, %s, %s %s, %s]", provider.name(), provider.getLocation().name().toUpperCase(), provider.getLocation().getCounty(), provider.getLocation().getZip(), provider.getSpecialty().toString().toUpperCase());
            String format = String.format("%s %s %s %s %s", date, timeslot, name, dobFormat, providerDetails);
            System.out.println(format);
        }
        System.out.println("** end of list **");
    }

    /**
     * Sorts and prints the list of appointments by date, then timeslot, and provider.
     */
    public void printByAppointment() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].compareByAppointment(appointments[j + 1]) > 0) {

                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        System.out.println("** Appointments ordered by date/time/provider **");
        for (int i = 0; i < size; i++) {
            String date = formatDate(appointments[i]);
            String timeslot = formatTimeslot(appointments[i].getTimeslot());
            String fname = appointments[i].getPatient().getFname();
            String lname = appointments[i].getPatient().getLname();
            String name = fname + " " + lname;
            Date dob = appointments[i].getPatient().getDob();
            String dobFormat = String.format("%d/%d/%d", dob.getMonth(), dob.getDay(), dob.getYear());
            Provider provider = appointments[i].getProvider();
            String providerDetails = String.format("[%s, %s, %s %s, %s]", provider.name(), provider.getLocation().name().toUpperCase(), provider.getLocation().getCounty(), provider.getLocation().getZip(), provider.getSpecialty().toString().toUpperCase());
            String format = String.format("%s %s %s %s %s", date, timeslot, name, dobFormat, providerDetails);
            System.out.println(format);
        }
        System.out.println("** end of list **");
    }

    /**
     * Formats the timeslot into a readable string format.
     *
     * @param timeslot the timeslot to format
     * @return a string representing the formatted timeslot
     */
    public String formatTimeslot(Timeslot timeslot) {
        if (timeslot.equals(Timeslot.SLOT1)) {
            return String.format("%d:%02d %s", 9, 00, "AM");
        } else if (timeslot.equals(Timeslot.SLOT2)) {
            return String.format("%d:%02d %s", 10, 45, "AM");
        } else if (timeslot.equals(Timeslot.SLOT3)) {
            return String.format("%d:%02d %s", 11, 15, "AM");
        } else if (timeslot.equals(Timeslot.SLOT4)) {
            return String.format("%d:%02d %s", 1, 30, "PM");
        } else if (timeslot.equals(Timeslot.SLOT5)) {
            return String.format("%d:%02d %s", 3, 00, "PM");
        } else if (timeslot.equals(Timeslot.SLOT6)) {
            return String.format("%d:%02d %s", 4, 15, "PM");
        }
        String returnThis = timeslot + " is not a valid time slot";
        return returnThis;
    }

    private String formatDate(Appointment appointment) {
        int day = appointment.getDate().getDay();
        int month = appointment.getDate().getMonth();
        int year = appointment.getDate().getYear();
        String formattedDate = String.format("%d/%d/%d", month, day, year);
        return formattedDate;
    }

    public void billingCommand() {
        if (size == 0) {
            System.out.println("There are no appointments in the system.");
            return;
        }

        System.out.println("** Billing statement ordered by patient **");
        sortAppByPatient();
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        int counter = 1;
        Profile currentProfile = null;
        int currentCharge = 0;

        for (int i = 0; i < size; i++) {
            Appointment appt = appointments[i];
            Profile profile = appt.getPatient();

            if(appt.getProvider() == null) {
                i++;
                break;
            }
            int charge = appt.getProvider().getSpecialty().getCharge();

            if (currentProfile == null || !currentProfile.equals(profile)) {
                if (currentProfile != null) {
                    System.out.printf("(%d) %s %s [amount due: %s]%n",
                            counter++,
                            currentProfile.getFname() + " " + currentProfile.getLname(),
                            formatDOB(currentProfile.getDob()), // Format DOB
                            df.format(currentCharge));
                }
                currentProfile = profile;
                currentCharge = charge;
            } else {
                currentCharge += charge;
            }
        }

        if (currentProfile != null) {
            System.out.printf("(%d) %s %s [amount due: %s]%n",
                    counter,
                    currentProfile.getFname() + " " + currentProfile.getLname(),
                    formatDOB(currentProfile.getDob()),
                    df.format(currentCharge));
        }

        System.out.println("** end of list **");
    }

    private String formatDOB(Date dob) {
        return String.format("%d/%d/%d", dob.getMonth(), dob.getDay(), dob.getYear());
    }

    private void sortAppByPatient() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].getPatient().getLname().compareTo(appointments[j + 1].getPatient().getLname()) > 0) {
                    // Swap appointments
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }
    }

    public int identifyAppointment(Profile profile, Date date, Timeslot timeslot) {
        for (int i = 0; i < size; i++) {
            if (appointments[i].getPatient().equals(profile)) {
                if (appointments[i].getDate().equals(date)) {
                    if (appointments[i].getTimeslot().equals(timeslot)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}