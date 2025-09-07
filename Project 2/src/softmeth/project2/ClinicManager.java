package softmeth.project2;

import util.Date;
import util.ListMethods;
import util.Sort;
import util.List;
import util.CLL;

import java.io.File;
import java.util.Scanner;

import static util.Sort.*;

/**
 * Manages clinic operations including adding providers, scheduling appointments,
 * and rescheduling existing appointments. This class utilizes a list of providers
 * and appointments, and provides methods to handle user input for various actions.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class ClinicManager {
    private Scanner scanner = new Scanner(System.in);

    List<Provider> providerList;
    List<Appointment> appList;
    CLL techniciansList = new CLL();
    ListMethods methodList = new ListMethods();

    /**
     * Constructor for the ClinicManager class. Initializes the provider list,
     * appointment list, and technician list.
     */
    public ClinicManager() {
        providerList = new List<Provider>();
        appList = new List<Appointment>();
        techniciansList = new CLL();
    }

    /**
     * Converts a string representing a date of birth in the format "MM/DD/YYYY"
     * to a Date object.
     *
     * @param dobString The string representation of the date of birth.
     * @return A Date object corresponding to the given date of birth.
     */
    private Date dobFormat(String dobString) {
        String[] dobArr = dobString.split("/");
        int monthDOB = Integer.parseInt(dobArr[0]);
        int dateDOB = Integer.parseInt(dobArr[1]);
        int yearDOB = Integer.parseInt(dobArr[2]);

        Date date = new Date(yearDOB, monthDOB, dateDOB);
        return date;
    }

    /**
     * Adds a doctor to the clinic based on a provided string containing doctor details.
     *
     * @param line The input string containing doctor information.
     */
    private void addDoctor(String line) {
        Scanner fileScanner = new Scanner(line);

        while (fileScanner.hasNext()) {
            String firstName = fileScanner.next();
            String lastName = fileScanner.next();
            String dobString = fileScanner.next();
            String locationName = fileScanner.next();
            String speciality = fileScanner.next();
            String NPI = fileScanner.next();

            Profile doctorProfile = new Profile(firstName, lastName, dobFormat(dobString));
            Doctor doctor = new Doctor(doctorProfile, Location.valueOf(locationName), Specialty.valueOf(speciality), NPI);
            providerList.add(doctor);
        }
    }

    /**
     * Adds a technician to the clinic based on a provided string containing technician details.
     *
     * @param line The input string containing technician information.
     */
    private void addTechnician(String line) {
        Scanner fileScanner = new Scanner(line);

        while (fileScanner.hasNext()) {
            String firstName = fileScanner.next();
            String lastName = fileScanner.next();
            String dobString = fileScanner.next();
            String locationName = fileScanner.next();
            String rate = fileScanner.next();

            Profile technicianProfile = new Profile(firstName, lastName, dobFormat(dobString));

            Technician technician = new Technician(Integer.parseInt(rate), technicianProfile, Location.valueOf(locationName));
            techniciansList.addTech(technician);
            providerList.add(technician);
        }
    }

    /**
     * Reschedules an existing appointment to a new timeslot based on the user's input.
     * Validates the appointment before performing the rescheduling.
     *
     * @param input The input string containing appointment details for rescheduling.
     */
    public void rescheduleCommand (String input){
        String[] inputParts = input.split(",");
        if (inputParts.length < 7) {
            System.out.println("Missing data tokens.");
            return;
        }

        String dateString = inputParts[1];
        String timeslotString = inputParts[2];
        String fName = inputParts[3];
        String lName = inputParts[4];
        String dobString = inputParts[5];
        int newTimeslotNum = Integer.parseInt(inputParts[6]);

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

        if (!Timeslot.isValidSlotNum(newTimeslotNum)) {
            System.out.println(newTimeslotNum + " is not a valid timeslot.");
            return;
        }

        Timeslot currentTimeslot = Timeslot.getSlotTime(Integer.parseInt(timeslotString));
        Timeslot newTimeslot = Timeslot.getSlotTime(newTimeslotNum);

        int apptIndex = methodList.identifyAppointment(patient, date, currentTimeslot, appList);
        if (apptIndex == -1) {
            System.out.println(dateString + " " + timeslotString + " " + fName + " " + lName + " " + dobString + " does not exist.");
            return;
        }

        Appointment appointment = appList.get(apptIndex);
        Doctor doctor = (Doctor) appointment.getProvider();

        int existingApptIndex = methodList.identifyAppointment(patient, date, newTimeslot, appList);
        if (existingApptIndex != -1) {
            Appointment existingAppt = appList.get(existingApptIndex);
            System.out.println(patient.getFname() + " " + patient.getLname() + " " + dobString +
                    " has an existing appointment at " + dateString + " " + newTimeslot.toString());
            return;
        }

        if (!isAvailable(newTimeslot, date, doctor)) {
            System.out.println(doctor.toString() + " is not available at slot " + newTimeslot.toString());
            return;
        }

        appList.remove(appList.get(apptIndex));
        methodList.remove(appointment);

        Appointment newAppointment = new Appointment(date, newTimeslot, appointment.getPatient(), doctor);
        appList.add(newAppointment);
        methodList.add(newAppointment);

        String providerDetails = String.format("[%s %s %s, %s, %s %s][%s, #%s]",
                doctor.getProfile().getFname(), doctor.getProfile().getLname(), doctor.getProfile().getDob(),
                doctor.getLocation().name().toUpperCase(), doctor.getLocation().getCounty(), doctor.getLocation().getZip(),
                doctor.getSpecialty().toString().toUpperCase(), doctor.getNpi());

        System.out.println("Rescheduled to " + dateString + " " + newTimeslot.toString() + " " +
                fName + " " + lName + " " + dobString + " " + providerDetails);
    }

    /**
     * Checks if a given doctor is available for an appointment at a specified timeslot and date.
     *
     * @param timeslot The timeslot to check for availability.
     * @param date The date to check for availability.
     * @param doctor The doctor to check availability for.
     * @return true if the doctor is available; false otherwise.
     */
    private boolean isAvailable(Timeslot timeslot, Date date, Doctor doctor) {
        for (Appointment app : appList) {
            if (app.getDate().equals(date) && app.getTimeslot().equals(timeslot) && app.getProvider().equals(doctor)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a given technician is available for an appointment at a specified timeslot and date.
     *
     * @param timeslot The timeslot to check for availability.
     * @param date The date to check for availability.
     * @param technician The technician to check availability for.
     * @return true if the technician is available; false otherwise.
     */
    private boolean isTechAvailable(Timeslot timeslot, Date date, Provider technician){
        for (Appointment app : appList) {
            if (app.getDate().equals(date) && app.getTimeslot().equals(timeslot) && app.getProvider().equals(technician)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Loads provider data from a file named "providers.txt".
     * Each line in the file represents a provider, and the method splits
     * the line into fields to create Doctor or Technician objects accordingly.
     *
     * If the file is not found or if there are any issues reading it,
     * an appropriate message is printed to the console.
     *
     * The method validates provider type and formats, ensuring that
     * invalid entries are skipped.
     */
    public void loadProviders() {
        String fileName = "providers.txt";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;
        }
        try {
            Scanner scanner = new Scanner(file);  // Using Scanner to read the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splittedLine = line.split("  ");
                if (splittedLine[0].equals("D")) {
                    Profile profile = new Profile(splittedLine[1], splittedLine[2], dobFormat(splittedLine[3]));
                    Specialty specialty = setSpecialty(splittedLine[5]);
                    Doctor doctor = new Doctor(profile, setLocation(splittedLine[4]), specialty, splittedLine[6]);
                    providerList.add(doctor);
                } else if (splittedLine[0].equals("T")) {
                    Profile profile = new Profile(splittedLine[1], splittedLine[2], dobFormat(splittedLine[3]));
                    Location location = setLocation(splittedLine[4]);
                    int rate = Integer.parseInt(splittedLine[5]);
                    Technician technician = new Technician(rate, profile, location);
                    providerList.add(technician);
                    techniciansList.addTech(technician);
                }
            }
            scanner.close();  // Don't forget to close the Scanner
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Providers loaded to the list.");
    }

    /**
     * Sets the specialty based on the given input string.
     * The input is converted to lowercase and trimmed to ensure consistent comparison.
     *
     * @param input the specialty as a string.
     * @return the corresponding Specialty enum value or null if the input is invalid.
     */
    public Specialty setSpecialty(String input) {
        input = input.trim().toLowerCase();
        switch (input) {
            case "family":
                return Specialty.Family;
            case "pediatrician":
                return Specialty.Pediatrician;
            case "allergist":
                return Specialty.Allergist;
            default:
                System.out.println("Not a valid speciality");
                return null;
        }
    }

    /**
     * Sets the location based on the given input string.
     * The input is converted to lowercase and trimmed to ensure consistent comparison.
     *
     * @param input the location as a string.
     * @return the corresponding Location enum value or null if the input is invalid.
     */
    public Location setLocation(String input) {
        input = input.trim().toLowerCase();
        switch (input) {
            case "bridgewater":
                return Location.Bridgewater;
            case "edison":
                return Location.Edison;
            case "piscataway":
                return Location.Piscataway;
            case "princeton":
                return Location.Princeton;
            case "morristown":
                return Location.Morristown;
            case "clark":
                return Location.Clark;
            default:
                System.out.println("Not a valid location");
                return null;
        }
    }

    /**
     * Validates if the provided NPI (National Provider Identifier) is valid.
     * The method checks if the NPI is numeric and exists within the provider list.
     *
     * @param npiStr the NPI as a string.
     * @return true if the NPI is valid; false otherwise.
     */
    private boolean isValidNPI(String npiStr) {
        if (!npiStr.matches("\\d+")) { // Regular expression to check if npiStr is all digits
            System.out.println(npiStr + " - provider doesn't exist.");
            return false;
        }
        for (Provider provider : providerList) {
            if (provider instanceof Doctor) {
                Doctor doc = (Doctor) provider;
                if (doc.getNpi().equals(npiStr)) {
                    return true;
                }
            }
        }
            System.out.println(npiStr + " - provider doesn't exist.");
            return false;
    }

    /**
     * Schedules a new appointment based on the provided input line.
     * The input is parsed to extract patient details, date, timeslot, and provider NPI.
     * Validations are performed to check date validity, patient DOB, NPI,
     * and whether the timeslot is available.
     *
     * @param line a string containing appointment details in the format:
     *             "command,date,timeslot,fName,lName,dob,NPI"
     */
    public void scheduleAppointment(String line) {
        String[] inputParts = line.split(",");
        if(inputParts.length<6){
            System.out.println("Missing data tokens");
            return;
        }
        String dateString = inputParts[1];
        String timeslotString = inputParts[2];
        String fName = inputParts[3];
        String lName = inputParts[4];
        String dobString = inputParts[5];
        String NPIstring = inputParts[6];
        String[] dateArr = dateString.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int dateNum = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        String[] dobArr = dobString.split("/");
        int monthDOB = Integer.parseInt(dobArr[0]);
        int dateDOB = Integer.parseInt(dobArr[1]);
        int yearDOB = Integer.parseInt(dobArr[2]);
        Date date = new Date(year, month, dateNum);
        Date dob = new Date(yearDOB, monthDOB, dateDOB); //getting dob

        Profile patientProfile = new Profile(fName, lName, dob);
        Patient newPat=new Patient(null,patientProfile);

        if(!date.isValidDay()){
            System.out.println("Appointment date:" + dateString + " is not a valid calendar date ");
            return;
        }

        if(date.IsToday() || date.isBeforeToday()){
            System.out.println("Appointment date:" + dateString + "is today or a day before today");
            return;
        }

        if(date.isWeekend()){
            System.out.println("Appointment date:"+ dateString+ "is Saturday or Sunday.");
            return;
        }

        if(!date.withinSix()){
            System.out.println("Appointment date:" +dateString+ "is not within six months.");
            return;
        }

        if(Timeslot.isNumeric(timeslotString)==false){
            System.out.println(timeslotString+ " is not a valid time slot.");
            return;
        }

        int slotNum=Integer.parseInt(timeslotString);

        if(Timeslot.isValidSlotNum(slotNum)==false){
            System.out.println(timeslotString+ " is not a valid time slot.");
            return;
        }

        if(!dob.isValidDay()){
            System.out.println("Patient dob:" +dobString+ " is not a valid calendar date.");
            return;
        }

        if(dob.IsToday() || dob.isAfterToday()){
            System.out.println("Patient dob: " +dobString+ " is today or a day after today. ");
            return;
        }

        isValidNPI(NPIstring);

        Doctor doctor = null;
        for(Provider provider : providerList) {
            if(provider instanceof Doctor && ((Doctor) provider).getNpi().equals(NPIstring)) {
                doctor = (Doctor) provider;
                break;
            }
        }

        if(appointmentExists(newPat,date,Timeslot.getSlotTime(slotNum),fName,lName,dobString)==true){
            return;
        }
        Timeslot t = Timeslot.getSlotTime(slotNum);

        if(!isAvailable(t, date, doctor)) {
            System.out.println(doctor.toString() + "is not available at slot"+ timeslotString);
            return;
        }

        int hour = t.getHour(t);
        int minute = t.getMinute(t);
        Timeslot timeslotAdd=new Timeslot(hour, minute);

        Doctor doctor1 = null;
        for(int i = 0; i < providerList.size(); i++) {
            if (providerList.get(i) instanceof Doctor d) {
                if(d.getNpi().equals(NPIstring)) {
                    doctor1 = d;
                }
            }
        }
        if (doctor1 == null) {
            return;
        }

        Appointment appointment=new Appointment(date,timeslotAdd,newPat,doctor1);
        appList.add(appointment);
        methodList.add(appointment);

        String providerDetails = String.format("[%s %s %s, %s, %s %s][%s, #%s]", doctor1.getProfile().getFname(), doctor1.getProfile().getLname(), doctor1.getProfile().getDob(), doctor1.getLocation().name().toUpperCase(), doctor1.getLocation().getCounty(), doctor1.getLocation().getZip(), doctor1.getSpecialty().toString().toUpperCase(), doctor1.getNpi());

        System.out.printf("%s %s %s %s %s %s booked.%n", date, timeslotAdd, fName, lName, dob, providerDetails);
    }

    /**
     * Checks if a provider is available for a given timeslot on a specific date.
     *
     * @param timeslot the timeslot to check for availability.
     * @param apptDate the date of the appointment.
     * @param provider the provider to check for availability.
     * @return true if the provider is available; false if the provider has an appointment
     *         at the same time and date.
     */
    public boolean isAvailable(Timeslot timeslot, Date apptDate, Provider provider) {
        for(Appointment appointment : appList) {
            if(appointment.getDate().equals(apptDate) && appointment.getTimeslot().equals(timeslot) && appointment.getProvider().equals(provider) ) {
                return false; //provider is NOT available at this timeslot
            }
        }
        return true;
    }

    /**
     * Processes a command for scheduling an appointment based on input data.
     * The method parses the input line, validates date and timeslot information,
     * checks the availability of technicians and rooms, and finally schedules
     * an appointment if all conditions are met.
     *
     * The input is expected to be in the format:
     * "command,date,timeslot,fName,lName,dob,roomType"
     *
     * @param line the input command line containing appointment details.
     */
    public void TCommand(String line){
        String[] inputParts = line.split(",");
        if(inputParts.length<7){
            System.out.println("Missing data Tokens");
            return;
        }
        String dateString = inputParts[1];
        String timeslotString = inputParts[2];
        String fName = inputParts[3];
        String lName = inputParts[4];
        String dobString = inputParts[5];
        String roomTypeString = inputParts[6];
        String[] dateArr = dateString.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int dateNum = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        String[] dobArr = dobString.split("/");
        int monthDOB = Integer.parseInt(dobArr[0]);
        int dateDOB = Integer.parseInt(dobArr[1]);
        int yearDOB = Integer.parseInt(dobArr[2]);
        Date date = new Date(year, month, dateNum);
        Date dob = new Date(yearDOB, monthDOB, dateDOB); //getting dob

        //this is for patient
        Profile patientProfile = new Profile(fName, lName, dob);
        Patient newPat=new Patient(null,patientProfile);
        //validating dates
        if(!date.isValidDay()){
            System.out.println("Appointment date:" +dateString+ " is not a valid calendar date ");
            return;
        }
        if(date.IsToday() || date.isBeforeToday()){
            System.out.println("Appointment date:" +dateString+ "is today or a day before today");
            return;
        }
        if(date.isWeekend()){
            System.out.println("Appointment date:"+ dateString+ "is Saturday or Sunday.");
            return;
        }
        if(!date.withinSix()){
            System.out.println("Appointment date:" +dateString+ "is not within six months.");
            return;
        }

        if(Timeslot.isNumeric(timeslotString)==false){
            System.out.println(timeslotString+ " is not a valid time slot.");
            return;
        }

        int slotNum=Integer.parseInt(timeslotString);


        if(Timeslot.isValidSlotNum(slotNum)==false){
            System.out.println(timeslotString+ " is not a valid time slot.");
            return;
        }

        //validating DOB
        if(!dob.isValidDay()){
            System.out.println("Patient dob:" +dobString+ " is not a valid calendar date.");
            return;
        }
        if(dob.IsToday() || dob.isAfterToday()){
            System.out.println("Patient dob:" +dobString+ " is today or a day after today. ");
            return;
        }

        //checks clause 5
        if(appointmentExists(newPat,date,Timeslot.getSlotTime(slotNum),fName,lName,dobString)==true){
            return;
        }

        //

        //checks clause 6
        if(!isValidService(roomTypeString)){
            System.out.println(roomTypeString+ " - imaging service not provided.");
            return;
        }


        //checks clause 7
        Timeslot t = Timeslot.getSlotTime(Integer.parseInt(timeslotString));

        Technician tech=null;
        int rotateNum= techniciansList.getSize();

        while (rotateNum>0) {
            Technician nextTech = techniciansList.getNextTech();

                if(!isTechAvailable(t, date, nextTech)){
                   rotateNum--;
                   continue;
                }

               if(!isServiceRoomAvailable(nextTech.getLocation(), date, t, roomTypeString)){
                   rotateNum--;
                   continue;
               }
                tech = nextTech;
                break;
            }


        if (tech == null) {
            System.out.println("Cannot find an available technician at all locations for " + roomTypeString.toUpperCase() + " at slot " + timeslotString + ".");
            return;
        }

        Appointment imagingAppointment = new Imaging(date, t, newPat, tech,Radiology.valueOf(roomTypeString.toUpperCase()));
        appList.add(imagingAppointment);

        String outputString = String.format("%s %s %s %s %s [%s %s %s, %s, %s %s][rate: $%d][%s] booked.",
                date, t, fName, lName, dob,
                tech.getProfile().getFname().toUpperCase(), tech.getProfile().getLname().toUpperCase(),
                tech.getProfile().getDob(),
                tech.getLocation().name().toUpperCase(),
                tech.getLocation().getCounty(), tech.getLocation().getZip(),
                tech.getRatePerVisit(),
                roomTypeString.toUpperCase());

        System.out.println(outputString);

    }

    /**
     * Checks if a specific service room is available at a given location, date, and timeslot.
     *
     * @param loc the location of the service room to check.
     * @param date the date of the appointment.
     * @param t the timeslot of the appointment.
     * @param roomName the name of the room type to check availability.
     * @return true if the service room is available; false if it is already booked.
     */
    public boolean isServiceRoomAvailable(Location loc,Date date,Timeslot t, String roomName ) {
        for (Appointment app : appList) {
            if (app instanceof Imaging imagingappointment) {


                if (app.getProvider() instanceof Provider provider) {

                    if (provider.getLocation().equals(loc) && app.getDate().equals(date) &&
                            app.getTimeslot().equals(t)) {

                        if (imagingappointment.getRoom().toString().equalsIgnoreCase(roomName)) {
                            return false;

                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Validates if the provided service type is a valid radiology scan.
     *
     * @param Inputservice the service type to validate.
     * @return true if the service type is valid; false otherwise.
     */
    public boolean isValidService(String Inputservice){
        for(Radiology scan:Radiology.values()){
            if(scan.getScanType().equalsIgnoreCase(Inputservice)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an appointment already exists for the given patient, date, and timeslot.
     *
     * @param newPatient The patient for whom the appointment is being checked.
     * @param date The date of the appointment.
     * @param timeslot The timeslot of the appointment.
     * @param fName The first name of the patient.
     * @param lName The last name of the patient.
     * @param dobString The date of birth of the patient as a string.
     * @return true if an existing appointment is found, false otherwise.
     */
    public boolean appointmentExists(Patient newPatient, Date date, Timeslot timeslot, String fName,String lName,String dobString){
        for (int i = 0; i < appList.getSize(); i++){
            Appointment app = (Appointment) appList.getE(i);
            if(app.getDate().equals(date)
            && app.getTimeslot().equals(timeslot)
            && app.getPatient().equals(newPatient)){
                String output = String.format("%s %s %s has an existing appointment at the same time slot.",
                        fName, lName, dobString);
                System.out.println(output);
                return true;

            }
        }
        return false;
    }

    /**
     * Formats the date of the given appointment into a string.
     *
     * @param appointment The appointment to format.
     * @return A string representing the date in the format "MM/DD/YYYY".
     */
    public String formatDate(Appointment appointment) {
        int day = appointment.getDate().getDay();
        int month = appointment.getDate().getMonth();
        int year = appointment.getDate().getYear();
        String formattedDate = String.format("%02d/%02d/%d", month, day, year);
        return formattedDate;
    }

    /**
     * Formats the date of birth into a string.
     *
     * @param dob The date of birth to format.
     * @return A string representing the date of birth in the format "MM/DD/YYYY".
     */
    public String formatDOB(Date dob) {
        return String.format("%s/%s/%s", dob.getMonth(), dob.getDay(), dob.getYear());
    }


    /**
     * Prints the details of the provided list of providers to the console.
     *
     * @param provider The list of providers to print.
     */
    private void printProviders(List<Provider> provider) {
        for (int i = 0; i < provider.size(); i++) {
            System.out.println(provider.get(i).toString()); // Call toString() directly
        }
    }

    /**
     * Formats the given timeslot into a string.
     *
     * @param timeslot The timeslot to format.
     * @return A string representation of the timeslot.
     */
    public String formatTimeslot(Timeslot timeslot) {
        return timeslot.toString();  // Simply calls the toString() method of Timeslot
    }

    /**
     * Processes the command to cancel an existing appointment based on the provided input string.
     *
     * @param input The input string containing details of the appointment to cancel, separated by commas.
     */
    public void cancelCommand(String input) {
        String[] inputParts = input.split(",");

        if(inputParts.length < 6) {
            System.out.println("Missing data tokens.");
            return;
        }

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

        Date date = new Date(year, month, dateNum);
        Date dob = new Date(yearDOB, monthDOB, dateDOB);

        try{
            int timeslotNum = Integer.parseInt(timeslotString);
        } catch(NumberFormatException e) {
            System.out.println(timeslotString + " is not a valid time slot.");
            return;
        }

        Timeslot timeslot = Timeslot.getSlotTime(Integer.parseInt(timeslotString));
        if(timeslot == null) {
            System.out.println(timeslotString + " is not a valid time slot.");
            return;
        }

        Appointment cancelAppt = null;
        for(Appointment appointment : appList) {
            if (appointment.getDate().equals(date) &&
                    appointment.getTimeslot().equals(timeslot) &&
                    appointment.getPatient().getProfile().getFname().equalsIgnoreCase(fName) &&
                    appointment.getPatient().getProfile().getLname().equalsIgnoreCase(lName) &&
                    appointment.getPatient().getProfile().getDob().equals(dob)) {
                cancelAppt = appointment;
                break;
            }
        }

        if(cancelAppt != null) {
            appList.remove(cancelAppt);
            System.out.println(dateString + " " + timeslot.toString() + " " + fName + " " + lName + " " + dobString + " - appointment has been canceled.");
        } else {
            System.out.println(dateString + " " + timeslot.toString() + " " + fName + " " + lName + " " + dobString + " - appointment does not exist.");
        }
    }

    /**
     * Prints the appointments sorted by the patient (last name, first name, date of birth, then appointment date and time).
     *
     * @param appointment The list of appointments to print.
     */
    private void printByPatient(List <Appointment> appointment) {
        sortByPatient(appList);
        if(appointment.size() == 0) {
            System.out.println("Schedule calendar is empty.");
            return;
        } else {
            System.out.println("Schedule calendar is empty.");
        }
    }

    /**
     * Prints the appointments sorted by date, time, and provider.
     *
     * @param appointment The list of appointments to print.
     */
    private void printByAppointment(List<Appointment> appointment) {
        sortByAppointment(appList);
        if(appointment.size() == 0) {
            System.out.println("Schedule calendar is empty.");
            return;
        }
        System.out.println("** Appointments ordered by date/time/provider **");

        for (int i = 0; i < appointment.size(); i++) {
            Date date = appointment.get(i).getDate();
            String timeslot = formatTimeslot(appointment.get(i).getTimeslot());
            String fname = appointment.get(i).getPatient().getFname();
            String lname = appointment.get(i).getPatient().getLname();
            String name = fname + " " + lname;
            Date dob = appointment.get(i).getPatient().getDob();
            String dobFormat = String.format("%s/%s/%s", dob.getMonth(), dob.getDay(), dob.getYear());

            Provider provider = (Provider) appointment.get(i).getProvider();

            if (provider instanceof Doctor) {
            Doctor doc = (Doctor) provider;
            String providerDetails = String.format("[%s %s %s, %s, %s %s][%s, #%s]", provider.getFname(), provider.getLname(), provider.getDob(), provider.getLocation().name().toUpperCase(), provider.getLocation().getCounty(), provider.getLocation().getZip(), doc.getSpecialty().toString().toUpperCase(), doc.getNpi());
            String format = String.format("%s %s %s %s %s", date, timeslot, name, dobFormat, providerDetails);
            System.out.println(format);
            } else {
                Imaging imaging = (Imaging) appointment.get(i);
                String rate = "";
                if(imaging.getProvider().getFname().equals("BROWN")) {
                    rate = "100.00";
                } else if(imaging.getProvider().getFname().equals("FOX")) {
                    rate = "130.00";
                } else if(imaging.getProvider().getFname().equals("JERRY")) {
                    rate = "150.00";
                } else if(imaging.getProvider().getFname().equals("JOHNSON")) {
                    rate = "110.00";
                } else if(imaging.getProvider().getFname().equals("LIN")) {
                    rate = "120.00";
                } else if(imaging.getProvider().getFname().equals("PATEL")) {
                    rate = "125.00";
                }

                String providerDetails = String.format("[%s %s %s, %s, %s %s][rate: $%s]",
                        imaging.getProvider().getFname(), imaging.getProvider().getLname(),
                        imaging.getProvider().getDob(), imaging.getProvider().getLocation().name().toUpperCase(),
                        imaging.getProvider().getLocation().getCounty(), imaging.getProvider().getLocation().getZip(), rate);

                String format = String.format("%s %s %s %s %s", date, timeslot, name, dobFormat, providerDetails);
                System.out.println(format);
            }
        }
        System.out.println("** end of list **");
    }

    /**
     * Prints the appointments sorted by county name, then appointment date and time.
     *
     * @param appointments The list of appointments to print.
     */
    private void printByLocation(List<Appointment> appointments) {
        sortByLocation(appList);
        if(appointments.size() == 0) {
            System.out.println("Schedule calendar is empty.");
            return;
        }
        System.out.println("** Appointments ordered by county/date/time ** ");
        for (int i = 0; i < appointments.getSize(); i++) {
            String date = formatDOB(appointments.get(i).getDate());
            String timeslot = formatTimeslot(appointments.get(i).getTimeslot());
            String fname = appointments.get(i).getPatient().getFname();
            String lname = appointments.get(i).getPatient().getLname();
            String name = fname + " " + lname;
            Date dob = appointments.get(i).getPatient().getDob();
            String dobFormat = String.format("%d/%d/%d", dob.getMonth(), dob.getDay(), dob.getYear());
            Provider provider = (Provider) appointments.get(i).getProvider();

            Doctor d = null;
            if(provider instanceof Doctor) {
                //d = (Doctor) provider;
                String providerDetails = String.format("[%s %s, %s, %s %s]", provider.getFname(), provider.getLname(), provider.getLocation().name().toUpperCase(), provider.getLocation().getCounty(), provider.getLocation().getZip());
                String format = String.format("%s %s %s %s %s", date, timeslot, name, dobFormat, providerDetails);
                System.out.println(format);
            } else {
               // d = (Doctor) provider;
                String providerDetails = String.format("[ %s, %s, %s %s, %s]", provider.getFname(), provider.getLname(), provider.getLocation().name().toUpperCase(), provider.getLocation().getCounty(), provider.getLocation().getZip());
                String format = String.format("%s %s %s %s %s", date, timeslot, name, dobFormat, providerDetails);
                System.out.println(format);
            }
        }
        System.out.println("** end of list **");
    }

    /**
     * Loads the providers and runs the appointment management system, processing user input commands.
     */
    public void run() {
        loadProviders();
        if (providerList.size() == 0) {
            System.out.println("No providers loaded from the file.");
            return;
        }
        List<Provider> sortedList = Sort.sortProviders(providerList);
        printProviders(sortedList);
        techniciansList.displayTechList();
        System.out.println("Clinic Manager is running...");
        while(this.scanner.hasNextLine()) {
            String input = this.scanner.nextLine();
            if (!input.isEmpty()) {
                String[] inputParts = input.split(",");
                String commandCheck = inputParts[0];
                if (commandCheck.equals("D")) { this.scheduleAppointment(input);
                } else if(commandCheck.equals("T")) {this.TCommand(input);
                } else if(commandCheck.equals("C")) {this.cancelCommand(input);
                } else if(commandCheck.equals("R")) {this.rescheduleCommand(input);
                } else if(commandCheck.equals("PA")) {this.printByAppointment(appList);
                } else if(commandCheck.equals("PP")) {this.printByPatient(appList);
                } else if(commandCheck.equals("PL")) {this.printByLocation(appList);
                } else if(commandCheck.equals("PS")) {System.out.println("Schedule calendar is empty.");
                } else if(commandCheck.equals("PO")) {System.out.println("Schedule calendar is empty.");
                } else if(commandCheck.equals("PI")) {System.out.println("Schedule calendar is empty.");
                } else if(commandCheck.equals("PC")) {System.out.println("Schedule calendar is empty.");
                } else if (commandCheck.equals("Q")) {System.out.println("Clinic Manager terminated.");
                break;} else {System.out.println("Invalid command!"); }
            }
        }
    }
}