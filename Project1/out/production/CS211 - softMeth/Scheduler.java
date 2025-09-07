import java.util.Scanner;


public class Scheduler {
    private List appointmentList = new List();
    Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Scheduler is running.");
        String input;
        while(true) {
            input = scanner.nextLine();


            if(input.isEmpty()) {
                continue;
            }


            String [] inputParts = input.split(",");
            String commandCheck = inputParts[0];


            //if-else statements to check what happens with the command entered
            if (commandCheck.equals("S")) { //schedule new appointment
                scheduleCommand(input);
            } else if (commandCheck.equals("C")) { //cancel existing appointment
                cancelCommand(input);
            } else if (commandCheck.equals("R")) { //reschedule appointment
                rescheduleCommand(input);
            } else if (commandCheck.equals("PA")) { //display list of appointments: sorted by appointment date, time, then provider’s name.
                if(appointmentList.getSize() == 0) {
                    System.out.println("The schedule calendar is empty.");
                } else {
                    appointmentList.printByAppointment(); }
            } else if (commandCheck.equals("PP")) { //display list of appointments: sorted by the patient (by last name, first name, date of birth, the appointment date and time).
                if(appointmentList.getSize() == 0) {
                    System.out.println("The schedule calendar is empty.");
                } else {
                    appointmentList.printByPatient(); }
            } else if (commandCheck.equals("PL")) { //display list of appointments: sorted by the county name, then the appointment date and time.
                if(appointmentList.getSize() == 0) {
                    System.out.println("The schedule calendar is empty.");
                } else {
                    appointmentList.printByLocation(); }
            } else if (commandCheck.equals("PS")) { //display billing statements
                //billingCommand();
            } else if (commandCheck.equals("Q")) { //stop execution
                System.out.println("Scheduler is terminated.");
                break;
            } else {
                System.out.println("Invalid Command!");
            }
        }
    }


    //helper method for S Command: schedule a new appointment and add the appointment to the calendar
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
                providerExists = true;
                break;
            }
        }

        if (!providerExists) {
            System.out.println(providerString + " - provider doesn't exist");
            return;
        }
        provider = Provider.valueOf(providerString);




        if (!(timeslotString.equals("1") || timeslotString.equals("2") ||
                timeslotString.equals("3") || timeslotString.equals("4") ||
                timeslotString.equals("5") || timeslotString.equals("6"))) {
            System.out.println(timeslotString + " is not a valid time slot.");
            return;
        }


        if(!dob.isValidDay()) {
            if(dob.IsToday()) {
                String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
                System.out.println("Patient dob: " + dobFormat + " is today or a date after today.");
                return;
            }
            if( (dob.isAfterToday())) {
                String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
                System.out.println("Patient dob: " + dobFormat + " is today or a date after today.");
                return;
            }
            String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
            System.out.println("Patient dob: " + dobFormat + " is not a calandar date");
            return;
        }


        Timeslot timeslot= Timeslot.values()[Integer.parseInt(timeslotString) - 1];
        Appointment newAppointment = new Appointment(date, timeslot, patient, provider);


        if (date.isValidDay()) { //if its a valid calender day
            if (date.isWeekend()) {
                String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
                System.out.println("Appointment date: " + dateFormat + " is Saturday or Sunday." );
                return; }
            if (date.isBeforeToday()) {
                String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
                System.out.println("Appointment date: " + dateFormat + " is today or a date before today." );
                return; }
            if (date.IsToday()) {
                String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
                System.out.println("Appointment date: " + dateFormat + " is today or a date before today." );
                return; }
            if (!date.withinSix()) {
                String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
                System.out.println("Appointment date: " + dateFormat + " is not within six months." );
                return; }


            appointmentList.add(newAppointment);
            String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
            String dobFormat = String.format("%d/%d/%d", monthDOB, dateDOB, yearDOB);
            String timeFormat = formatTimeslot(timeslot);


            String providerDetails = String.format("[%s, %s, %s, %s, %s]", provider.name(), provider.getLocation().name().toUpperCase(), provider.getLocation().getCounty(), provider.getLocation().getZip(),provider.getSpecialty().toString().toUpperCase());


            System.out.printf("%s %s %s %s %s %s booked.%n", dateFormat, timeFormat, fName, lName, dobFormat, providerDetails);
        } else {
            String dateFormat = String.format("%d/%d/%d", month, dateNum, year);
            System.out.println("Appointment date: " + dateFormat + " is not a valid calendar date." );
        }
    }


    private String formatTimeslot(Timeslot timeslot) {
        if(timeslot.equals(Timeslot.SLOT1)) {
            return String.format("%d:%02d %s", 9, 00, "AM");
        } else if(timeslot.equals(Timeslot.SLOT2)) {
            return String.format("%d:%02d %s", 10, 45, "AM");
        } else if(timeslot.equals(Timeslot.SLOT3)) {
            return String.format("%d:%02d %s", 11, 15, "AM");
        } else if(timeslot.equals(Timeslot.SLOT4)) {
            return String.format("%d:%02d %s", 1, 30, "PM");
        } else if(timeslot.equals(Timeslot.SLOT5)) {
            return String.format("%d:%02d %s", 3, 00, "PM");
        } else if(timeslot.equals(Timeslot.SLOT6)) {
            return String.format("%d:%02d %s", 4, 15, "PM");
        }
        String returnThis = timeslot + " is not a valid time slot";
        return returnThis;
    }




    public void cancelCommand(String input) {
        String [] inputParts = input.split(",");




        String dateString = inputParts[1];
        String timeslotString = inputParts[2];
        String fName = inputParts[3];
        String lName = inputParts[4];
        String dobString = inputParts[5];




        String[] dateArr = dateString.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int dateNum = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);




        String[] dobArr = dobString.split("/");
        int monthDOB = Integer.parseInt(dobArr[0]);
        int dateDOB = Integer.parseInt(dobArr[1]);
        int yearDOB = Integer.parseInt(dobArr[2]);




        Date date = new Date(month, dateNum, year);
        Date dob = new Date(monthDOB, dateDOB, yearDOB);




        Timeslot timeslot = Timeslot.values()[Integer.parseInt(timeslotString)];
        Profile patient = new Profile(fName, lName, dob);




        Appointment appointment = new Appointment(date, timeslot, patient, null);




        if(appointmentList.contains(appointment)) {
            appointmentList.remove(appointment);
        }
    }




    public void rescheduleCommand(String input) {
        String [] inputParts = input.split(",");




        String dateString = inputParts[1];
        String timeslotString = inputParts[2];
        String fName = inputParts[3];
        String lName = inputParts[4];
        String dobString = inputParts[5];
        String timeslot2 = inputParts[6];




        String[] dateArr = dateString.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int dateNum = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);




        String[] dobArr = dobString.split("/");
        int monthDOB = Integer.parseInt(dobArr[0]);
        int dateDOB = Integer.parseInt(dobArr[1]);
        int yearDOB = Integer.parseInt(dobArr[2]);




        Date date = new Date(month, dateNum, year);
        Date dob = new Date(monthDOB, dateDOB, yearDOB);




        Timeslot timeslot = Timeslot.values()[Integer.parseInt(timeslotString)];
        Timeslot newTimeslot = Timeslot.values()[Integer.parseInt(timeslot2)];
        Profile patient = new Profile(fName, lName, dob);




        Appointment oldAppointment = new Appointment(date, timeslot, patient, null);
        Appointment newAppointment = new Appointment(date, newTimeslot, patient, null);




        if(appointmentList.contains(oldAppointment)) {
            //check if the new timeslot for rescheduling is already taken:
            if(!appointmentList.contains(newAppointment)) {
                appointmentList.add(newAppointment);
            }
        }
    }
}





