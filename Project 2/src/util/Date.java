package util;

import java.util.Calendar;

/**
 * This class represents a date with year, month, and day attributes.
 * It provides various methods to validate dates, check conditions like
 * whether the date is a weekend or today, and determine if the date
 * falls within a specified range.
 *
 * The class implements the Comparable interface, allowing Date objects
 * to be compared based on their chronological order.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    int date;
    int[] daysFprMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * Constructs a Date object with the specified year, month, and day.
     *
     * @param year the year of the date
     * @param month the month of the date (1-12)
     * @param day the day of the month (1-31)
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.day = day;
        this.month = month;
    }

    /**
     * Returns the day of the month.
     *
     * @return the day of the month
     */
    public int getDay() {
        return this.date;
    }

    /**
     * Returns the month of the date.
     *
     * @return the month of the date
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Returns the year of the date.
     *
     * @return the year of the date
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Compares this Date object with another Date object.
     *
     * @param date2 the Date object to be compared
     * @return a negative integer, zero, or a positive integer as this date
     *         is less than, equal to, or greater than the specified date
     */
    @Override
    public int compareTo(Date date2){
        if(this.year!=date2.year){
            return Integer.compare(this.year,date2.year);
        }
        if(this.month!=date2.month){
            return Integer.compare(this.month,date2.month);
        }
        return Integer.compare(this.day,date2.day);
    }

    /**
     * Checks if this date is equal to another date.
     *
     * @param anotherDate the date to compare
     * @return true if the dates are equal; false otherwise
     */
    @Override
    public boolean equals(Object anotherDate){
        if(this==anotherDate){
            return true;
        }
        if(anotherDate==null|| !(anotherDate instanceof Date)){
            return false;
        }
        Date newDate=(Date) anotherDate;
        return  year ==newDate.year && //checks if all 3 conditions are equal to each other after casting
                month == newDate.month &&
                day ==newDate.day;

    }

    /**
     * Returns a string representation of this date in the format MM/DD/YYYY.
     *
     * @return formatted string of the date
     */
    @Override
    public String toString(){
        return String.format("%02d/%02d/%04d",month,day,year);
    }

    /**
     * Checks if this date is today.
     *
     * @return true if the date is today; false otherwise
     */
    public boolean IsToday() {
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        int dayNow = calendar.get(Calendar.DAY_OF_MONTH);
        int monthNow = calendar.get(Calendar.MONTH) + 1;

        if (yearNow == this.year && dayNow == this.day && monthNow == this.month) {
            return true;
        }
        return false;

    }

    /**
     * Checks if this date falls on a weekend.
     *
     * @return true if the date is a Saturday or Sunday; false otherwise
     */
    public boolean isWeekend() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        //setting the calendar date to the day that you're taking in
        calendar.set(Calendar.MONTH, this.month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, this.day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }

    /**
     * Checks if this date is within six months from today.
     *
     * @return true if the date is within six months; false otherwise
     */
    public boolean withinSix() {
        Calendar calendar = Calendar.getInstance();
        Calendar newCal = Calendar.getInstance();

        newCal.add(Calendar.MONTH, 6);
        Calendar appDate = Calendar.getInstance();
        appDate.set(Calendar.YEAR, this.year);
        appDate.set(Calendar.MONTH, this.month - 1);
        appDate.set(Calendar.DAY_OF_MONTH, this.day);

        if (appDate.after(newCal)) {
            return false;
        }
        return true;
    }

    /**
     * Checks if this date is before today.
     *
     * @return true if the date is before today; false otherwise
     */
    public boolean isBeforeToday() {
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        int monthNow = calendar.get(Calendar.MONTH) + 1; //+1 bc it starts at 0
        int dayNow = calendar.get(Calendar.DAY_OF_MONTH);

        if (this.year < yearNow) {
            return true;
        } else if (this.year == yearNow) {
            if (this.month < monthNow) {
                return true;
            } else if (this.month == monthNow && this.day < dayNow) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this date is after today.
     *
     * @return true if the date is after today; false otherwise
     */
    public boolean isAfterToday(){
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        int monthNow = calendar.get(Calendar.MONTH) + 1; //+1 bc it starts at 0
        int dayNow = calendar.get(Calendar.DAY_OF_MONTH);

        if (this.year > yearNow) {
            return true;
        } else if (this.year == yearNow) {
            if (this.month > monthNow) {
                return true;
            } else if (this.month == monthNow && this.day > dayNow) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the month of this date is valid (1-12).
     *
     * @return true if the month is valid; false otherwise
     */    public boolean isValidMonth(){
        if(this.month<=12 && this.month>=1){
            return true;
        }
        return false;
    }

    /**
     * Returns the number of days in the specified month and year.
     *
     * @param month the month to check (1-12)
     * @param year the year to check
     * @return the number of days in the month
     */
    public int daysInMonth(int month,int year){
        int maxDay;
        if(month==1){
            maxDay=daysFprMonth[0];
            return maxDay; }
        if(month==2 && isLeapYear(year)){
            maxDay=29;
            return maxDay; }
        if(month==2 && !isLeapYear(year)){
            maxDay=daysFprMonth[1];
            return maxDay; }
        if(month==3){
            maxDay=daysFprMonth[2];
            return maxDay; }
        if(month==4){
            maxDay=daysFprMonth[3];
            return maxDay; }
        if(month==5){
            maxDay=daysFprMonth[4];
            return maxDay; }
        if(month==6){
            maxDay=daysFprMonth[5];
            return maxDay; }
        if(month==7){
            maxDay=daysFprMonth[6];
            return maxDay; }
        if(month==8){
            maxDay=daysFprMonth[7];
            return maxDay; }
        if(month==9){
            maxDay=daysFprMonth[8];
            return maxDay; }
        if(month==10){
            maxDay=daysFprMonth[9];
            return maxDay; }
        if(month==11){
            maxDay=daysFprMonth[10];
            return maxDay; }
        if(month==12){
            maxDay=daysFprMonth[11];
            return maxDay; }
        return -1; }

    /**
     * Checks if the specified year is a leap year.
     *
     * @param year the year to check
     * @return true if the year is a leap year; false otherwise
     */
    public boolean isLeapYear(int year) {
        if(year%QUATERCENTENNIAL==0){
            return true; //if divisble by 400 then leap year
        }
        else if(year%CENTENNIAL==0){
            return false;
        }
        else if(year%QUADRENNIAL==0){
            return true;
        }
        return false;
    }

    /**
     * Checks if the day of this date is valid for the specified month and year.
     *
     * @return true if the day is valid; false otherwise
     */
    public boolean isValidDay() {
        if(this.day>31){
            return false;
        }
        int maxMonthDays=daysInMonth(this.month,this.year);
        if(this.day>=1 && this.day<=maxMonthDays){
            return true;
        }
        return false;
    }

    /**
     * Checks if the day of this date is valid for the specified month and year.
     *
     * @return true if the day is valid; false otherwise
     */
    public boolean isValid() {
        return isValidDay()&& !IsToday() && !isBeforeToday() && withinSix() && !isWeekend();
    }

    /**
     * Main method to test the functionality of the Date class.
     * It creates several Date objects and checks their validity
     * based on various criteria, printing the results to the console.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        //not valid Day
        Date test1 = new Date(1989, 12, 32);
        boolean yes = test1.isValid();  // call the instance method
        System.out.println("We can schedule an appointment on this day: " + yes);

        //return false bc it is today
        Date test2 = new Date(2024, 9, 30);
        boolean yes1 = test2.isValid();  // call the instance method  should return VALID
        System.out.println("We can schedule an appointment on this day: " + yes1);

        //within  6 months
        Date test3 = new Date(2025, 1, 13);
        boolean yes3 = test3.isValid();  // call the instance method should return INVALID
        System.out.println("We can schedule an appointment on this day: " + yes3);

        //isWeekend yes so returns false
        Date test4=new Date(2024,9,29);
        boolean yes4= test4.isValid();  // call the instance method should return INVALID
        System.out.println("We can schedule an appointment on this day: " + yes4);

        //is a valid date returns true
        Date test5=new Date(2022,2,4);
        boolean yes5=test5.isValid();
        System.out.println("We can schedule an appointment on this day: " + yes5);

        Date test6= new Date(2024,10,11);
        boolean yes6=test6.isValid();
        System.out.println("We can schedule an appointment on this day: " +yes6);
    }
}
