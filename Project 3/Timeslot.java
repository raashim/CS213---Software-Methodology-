package com.example.uiruclinic.src.softmeth.project2;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Represents a time slot in a 12-hour format with hours and minutes.
 * This class provides methods to validate and manipulate time slots.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarthy
 */
public class Timeslot implements Comparable<Timeslot> {
    private int hour;
    private int minute;

    @FXML
    private static TextArea outputTextArea;

    /**
     * Constructs a Timeslot object with specified hour and minute.
     *
     * @param hour   The hour of the time slot (0-23).
     * @param minute The minute of the time slot (0-59).
     */
    public Timeslot(int hour, int minute ){
       this.hour=hour;
       this.minute=minute;
    }

    /**
     * Validates if the provided slot number is within the valid range.
     *
     * @param slotNumber The time slot number to validate (1-12).
     * @return true if the slot number is valid; false otherwise.
     */
    public static boolean isValidSlotNum(int slotNumber){
        int num1=1;
        int num12=12;
        if(slotNumber<num1){
            return false;
        }
        if(slotNumber>num12){
            return false;
        }
        return true;
    }

    /**
     * Checks if a string represents a numeric value.
     *
     * @param timeslotString The string to check.
     * @return true if the string is numeric; false otherwise.
     */
    public static boolean isNumeric(String timeslotString) {
        return timeslotString.matches("\\d+");
    }

    /**
     * Converts a given time slot number into a Timeslot object.
     *
     * @param slotNumber The time slot number (1-12).
     * @return A Timeslot object representing the corresponding time.
     * @throws IllegalArgumentException if the slot number is invalid.
     */
    public static Timeslot getSlotTime(int slotNumber){

       if(!isValidSlotNum(Integer.parseInt(String.valueOf(slotNumber)))){
            outputTextArea.appendText("Invalid time slot");
        }
        if(slotNumber<=6){
            int thirty=30;
            int min=(slotNumber-1)*thirty;
            return new Timeslot(9+min/60, min%60);
        }
        else{
            int thirty=30;
            int min=(slotNumber-7)*thirty;
            return new Timeslot(14+min/60, min%60);
        }
    }

    /**
     * Compares this Timeslot to another Timeslot for equality.
     *
     * @param second The object to compare to this Timeslot.
     * @return true if this Timeslot is equal to the specified object;
     * false otherwise.
     */
    @Override
    public boolean equals(Object second){
        if(this==second){ //same memory location means same
            return true;
        }
        if(!(second instanceof Timeslot)){
            return false;  //not same object type
        }

        Timeslot slot2=(Timeslot) second;
        if(this.hour==slot2.hour && this.minute==slot2.minute ){
            return true;
        }
        return false;
    }

    /**
     * Compares this Timeslot to another Timeslot.
     *
     * @param second The Timeslot to compare to this Timeslot.
     * @return A negative integer, zero, or a positive integer as this
     * Timeslot is less than, equal to, or greater than the specified
     * Timeslot.
     */
    @Override
    public int compareTo(Timeslot second){
        if(this.hour!=second.hour){
            return Integer.compare(this.hour,second.hour);
        }
        return Integer.compare(this.minute,second.minute);
    }

    /**
     * Returns a string representation of this Timeslot.
     *
     * @return A string in the format "hh:mm AM/PM" representing this
     * Timeslot.
     */
    @Override
    public String toString(){
        int Dhour;
        String morN;

        if(hour<12){
            morN="AM";
        }
        else{
            morN="PM";
        }
        if(hour%12==0){
            Dhour=12;
        }
        else{
            Dhour=hour%12;
        }
        return String.format("%02d:%02d %s", Dhour, minute, morN);
    }

    /**
     * Gets the hour of the specified Timeslot.
     *
     * @param timeslot The Timeslot object to get the hour from.
     * @return The hour of the specified Timeslot.
     */
    public int getHour(Timeslot timeslot) {
        return timeslot.hour;
    }

    /**
     * Gets the minute of the specified Timeslot.
     *
     * @param timeslot The Timeslot object to get the minute from.
     * @return The minute of the specified Timeslot.
     */
    public int getMinute(Timeslot timeslot) {
        return timeslot.minute;
    }
}