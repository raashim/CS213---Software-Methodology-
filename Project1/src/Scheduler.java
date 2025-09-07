import java.util.Scanner;

/**
 * This class represents a scheduler that manages appointments.
 * It allows users to schedule, cancel, reschedule, and view appointments.
 * The appointments can be displayed in various sorted orders.
 */
public class Scheduler {
    private List appointmentList = new List();
    Scanner scanner = new Scanner(System.in);

    /**
     * Runs the scheduler and processes user input commands.
     * Commands include scheduling, canceling, rescheduling, and displaying appointments.
     */
    public void run() {
        System.out.println("Scheduler is running.");
        String input;
        while (true) {
            input = scanner.nextLine();

            if (input.isEmpty()) {
                continue;
            }

            String[] inputParts = input.split(",");
            String commandCheck = inputParts[0];

            //if-else statements to check what happens with the command entered
            if (commandCheck.equals("S")) { //schedule new appointment
                scheduleCommand(input);
            } else if (commandCheck.equals("C")) { //cancel existing appointment
                cancelCommand(input);
            } else if (commandCheck.equals("R")) { //reschedule appointment
                rescheduleCommand(input);
            } else if (commandCheck.equals("PA")) { //display list of appointments: sorted by appointment date, time, then provider’s name.
                if (appointmentList.getSize() == 0) {
                    System.out.println("The schedule calendar is empty.");
                } else {
                    appointmentList.printByAppointment();
                }
            } else if (commandCheck.equals("PP")) { //display list of appointments: sorted by the patient (by last name, first name, date of birth, the appointment date and time).
                if (appointmentList.getSize() == 0) {
                    System.out.println("The schedule calendar is empty.");
                } else {
                    appointmentList.printByPatient();
                }
            } else if (commandCheck.equals("PL")) { //display list of appointments: sorted by the county name, then the appointment date and time.
                if (appointmentList.getSize() == 0) {
                    System.out.println("The schedule calendar is empty.");
                } else {

                    appointmentList.printByLocation();
                }
            } else if (commandCheck.equals("PS")) { //display billing statements
                appointmentList.billingCommand();
            } else if (commandCheck.equals("Q")) { //stop execution
                System.out.println("Scheduler is terminated.");
                break;
            } else {
                System.out.println("Invalid Command!");
            }
        }
    }

    /**
     * Checks if the given appointment already exists in the appointment list.
     * @param newApp the new appointment to check.
     * @return true if the appointment exists, false otherwise.
     */
    public boolean appExistsAlready(Appointment newApp) {
        for (int i = 0; i < appointmentList.getSize(); i++) {
            Appointment appointment = appointmentList.getAppointment(i);
            if (appointment.getDate().equals(newApp.getDate()) &&
                    appointment.getTimeslot().equals(newApp.getTimeslot()) &&
                    appointment.getPatient().equals(newApp.getPatient()) &&
                    appointment.getProvider().equals(newApp.getProvider())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a conflict with the given appointment in terms of date, timeslot, and provider.
     * @param appointment the appointment to check for conflicts.
     * @return true if there is a conflict, false otherwise.
     */
    private boolean conflictApp(Appointment appointment) {
        for (int i = 0; i < appointmentList.getSize(); i++) {
            Appointment app = appointmentList.getAppointment(i);
            if (app.getDate().equals(appointment.getDate()) &&
                    app.getTimeslot().equals(appointment.getTimeslot()) &&
                    app.getProvider().equals(appointment.getProvider())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Schedules a new appointment based on the user's input.
     * Validates the date, timeslot, provider, and other details before scheduling.
     * @param input the input string containing appointment details.
     */
    public void scheduleCommand(String input) {
        String[] inputParts = input.split(",");

        String dateString = inputParts[1];
        String timeslotString = inputParts[2];
        String fName = inputParts[3];
        String lName = inputParts[4];
        String dobString = inputParts[5];
        String providerString = inputParts[6];

        String[] dateArr = dateString.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int dateNum = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);

        String[] dobArr = dobString.split("/");
        int monthDOB = Integer.parseInt(dobArr[0]);
        int dateDOB = Integer.parseInt(dobArr[1]);
        int yearDOB = Integer.parseInt(dobArr[2]);

        Date date = new Date(year, month, dateNum);
        Date dob = new Date(yearDOB, monthDOB, dateDOB);
        Profile patient = new Profile(fName, lName, dob);

        Provider provider = null;
        boolean providerExists = false;
        for (Provider p : Provider.values()) {
            if (p.name().equalsIgnoreCase(providerString)) {
                provider = p;
                providerExists = true;
                break;
            }
        }

        if (!providerExists) {
            System.out.println(providerString + " - provider doesn't exist");
            return;
        }

        if (!(timeslotString.equals("1") || timeslotString.equals("2") ||
                timeslotString.equals("3") || timeslotString.equals("4") ||
                timeslotString.equals("5") || timeslotString.equals("6"))) {
            System.out.println(timeslotString + " is not a valid time slot.");
            return;
        }

        if (!dob.isValidDay()) {
            String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
            System.out.println("Patient dob: " + dobFormat + " is not a valid calendar date.");
            return;
        }

        if ((dob.isAfterToday())) {
            String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
            System.out.println("Patient dob: " + dobFormat + " is today or a date after today.");
            return;
        }
        if (dob.IsToday()) {
            String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
            System.out.println("Patient dob: " + dobFormat + " is today or a date after today.");
            return;
        }

        Timeslot timeslot = Timeslot.values()[Integer.parseInt(timeslotString) - 1];
        Appointment newAppointment = new Appointment(date, timeslot, patient, provider);

        if (date.isValidDay()) { //if its a valid calender day
            if (date.isWeekend()) {
                String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
                System.out.println("Appointment date: " + dateFormat + " is Saturday or Sunday.");
                return;
            }
            if (appExistsAlready(newAppointment)) {
                System.out.printf("%s %s %s has an existing appointment at the same time slot.%n", fName, lName, dobString);
                return;
            }

            if (date.isBeforeToday()) {
                String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
                System.out.println("Appointment date: " + dateFormat + " is today or a date before today.");
                return;
            }
            if (date.IsToday()) {
                String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
                System.out.println("Appointment date: " + dateFormat + " is today or a date before today.");
                return;
            }
            if (!date.withinSix()) {
                String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
                System.out.println("Appointment date: " + dateFormat + " is not within six months.");
                return;
            }

            if (conflictApp(newAppointment)) {
                String providerDetails = String.format("[%s, %s, %s %s, %s]", provider.name(), provider.getLocation().name().toUpperCase(), provider.getLocation().getCounty(), provider.getLocation().getZip(), provider.getSpecialty().toString().toUpperCase());
                System.out.println(providerDetails + " is not available at slot " + timeslotString + ".");
                return;
            }

            appointmentList.add(newAppointment);
            String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
            String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
            String timeFormat = formatTimeslot(timeslot);

            String providerDetails = String.format("[%s, %s, %s %s, %s]", provider.name(), provider.getLocation().name().toUpperCase(), provider.getLocation().getCounty(), provider.getLocation().getZip(), provider.getSpecialty().toString().toUpperCase());

            System.out.printf("%s %s %s %s %s %s booked.%n", dateFormat, timeFormat, fName, lName, dobFormat, providerDetails);
        } else {
            String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
            System.out.println("Appointment date: " + dateFormat + " is not a valid calendar date.");
        }
    }

    /**
     * Formats the timeslot to a human-readable string format.
     * @param timeslot the timeslot to format.
     * @return the formatted string representation of the timeslot.
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

    /**
     * Cancels an existing appointment based on the user's input.
     * Validates and removes the appointment from the list if found.
     * @param input the input string containing appointment details to cancel.
     */
    public void cancelCommand(String input) {
        String[] inputParts = input.split(",");

        String dateString = inputParts[1];
        String timeslotString = inputParts[2];
        String fName = inputParts[3];
        String lName = inputParts[4];
        String dobString = inputParts[5];
        String providerString = inputParts[6];

        String[] dateArr = dateString.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int dateNum = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);

        String[] dobArr = dobString.split("/");
        int monthDOB = Integer.parseInt(dobArr[0]);
        int dateDOB = Integer.parseInt(dobArr[1]);
        int yearDOB = Integer.parseInt(dobArr[2]);

        Date date = new Date(year, month, dateNum);
        Date dob = new Date(yearDOB, monthDOB, dateDOB);

        Timeslot timeslot = Timeslot.values()[Integer.parseInt(timeslotString) - 1];
        Profile patient = new Profile(fName, lName, dob);
        Provider provider3 = Provider.valueOf(providerString.toUpperCase());

        Appointment appointment = new Appointment(date, timeslot, patient, provider3);

        if (appointmentList.contains(appointment)) {
            appointmentList.remove(appointment);
            String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
            String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
            String timeFormat = formatTimeslot(timeslot);

            String providerDetails = String.format("[%s, %s, %s, %s, %s]", provider3.name(), provider3.getLocation().name().toUpperCase(), provider3.getLocation().getCounty(), provider3.getLocation().getZip(), provider3.getSpecialty().toString().toUpperCase());
            System.out.printf("%s %s %s %s %s has been canceled.%n", dateFormat, timeFormat, fName, lName, dobFormat, providerDetails);
        } else {
            String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
            String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
            String timeFormat = formatTimeslot(timeslot);
            System.out.printf("%s %s %s %s %s does not exist.%n", dateFormat, timeFormat, fName, lName, dobFormat);
        }
    }

    /**
     * Reschedules an existing appointment to a new timeslot based on the user's input.
     * Validates the appointment before performing the rescheduling.
     * @param input the input string containing appointment details for rescheduling.
     */
    public void rescheduleCommand (String input){
            String[] inputParts = input.split(",");

            String dateString = inputParts[1];
            String timeslotString = inputParts[2];
            String fName = inputParts[3];
            String lName = inputParts[4];
            String dobString = inputParts[5];
            int timeslot2 = Integer.parseInt(inputParts[6]);

            String[] dateArr = dateString.split("/");
            int month = Integer.parseInt(dateArr[0]);
            int dateNum = Integer.parseInt(dateArr[1]);
            int year = Integer.parseInt(dateArr[2]);

            String[] dobArr = dobString.split("/");
            int monthDOB = Integer.parseInt(dobArr[0]);
            int dateDOB = Integer.parseInt(dobArr[1]);
            int yearDOB = Integer.parseInt(dobArr[2]);

            Date date = new Date(year, month, dateNum);
            Date dob = new Date(yearDOB, monthDOB, dateDOB);

            Timeslot timeslot = Timeslot.values()[Integer.parseInt(timeslotString) - 1];
            Profile patient = new Profile(fName, lName, dob);

            Appointment oldAppointment = new Appointment(date, timeslot, patient, null);
            if (!appointmentList.contains((oldAppointment))) {
                String timeFormat = formatTimeslot(timeslot);
                System.out.println(dateString + " " + timeFormat + " " + fName + " " + lName + " " + dobString + " does not exist.");
                return;
            }

            if (timeslot2 < 1 || timeslot2 > 6) {
                String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
                String timeFormat = formatTimeslot(timeslot);
                String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
                System.out.printf("%s %s %s %s %s does not exist", dateFormat, timeFormat,fName,lName, dobFormat);
                return;
            }
            Timeslot newTimeslot = Timeslot.values()[timeslot2 - 1];
            Appointment newAppointment = new Appointment(date, newTimeslot, patient, null);

            if (appointmentList.contains(oldAppointment)) {

                if (!appointmentList.contains(newAppointment)) {
                    if (conflictApp(newAppointment)) {
                        System.out.printf("This provider is not available at slot" + timeslotString);
                    }
                    appointmentList.add(newAppointment);
                    String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
                    String timeFormat = formatTimeslot(newTimeslot);
                    String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
                    System.out.printf("Rescheduled to %s %s %s %s %s",dateFormat,timeFormat,fName,lName,dobFormat);

                }
            }

        }
    }