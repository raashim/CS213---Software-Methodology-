import java.util.Calendar;

/**
 * This class represents a Date object with year, month, and day attributes.
 * It provides methods for comparing dates, validating dates, and determining
 * specific properties of the date, such as if it is a weekend, today, or within
 * six months from the current date.
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
     * @param year  the year of the date
     * @param month the month of the date
     * @param day   the day of the date
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.day = day;
        this.month = month;
    }

    /**
     * Retrieves the year of this Date.
     *
     * @return the year of the Date
     */
    public int getYear() {
        return year;
    }

    /**
     * Retrieves the month of this Date.
     *
     * @return the month of the Date
     */
    public int getMonth() {
        return month;
    }

    /**
     * Retrieves the day of this Date.
     *
     * @return the day of the Date
     */
    public int getDay() {
        return day;
    }

    /**
     * Compares this Date to another Date to determine their relative ordering.
     *
     * @param o the Date to compare to
     * @return 1 if this Date is later, -1 if earlier, and 0 if they are equal
     */
    @Override
    public int compareTo(Date o) {
        int yearComparison = this.year - o.year;
        int monthComparison = this.month - o.month;
        int dayComparison = this.day - o.day;

        if (yearComparison > 0) {
            return 1;
        } else if (yearComparison < 0) {
            return -1;
        }

        if (monthComparison > 0) {
            return 1;
        } else if (monthComparison < 0) {
            return -1;
        }

        if (dayComparison > 0) {
            return 1;
        } else if (dayComparison < 0) {
            return -1;
        }

        return 0;
    }

    /**
     * Checks if this Date is equal to another object.
     *
     * @param anotherDate the object to compare to
     * @return true if the two Dates are equal, false otherwise
     */
    @Override
    public boolean equals(Object anotherDate) {
        if (this == anotherDate) {
            return true;
        }
        if (anotherDate == null || !(anotherDate instanceof Date)) {
            return false;
        }
        Date newDate = (Date) anotherDate;
        return year == newDate.year &&
                month == newDate.month &&
                day == newDate.day;
    }

    /**
     * Returns a string representation of this Date in MM/DD/YYYY format.
     *
     * @return the formatted string of the Date
     */
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }

    /**
     * Determines if this Date is today's date.
     *
     * @return true if this Date matches today's date, false otherwise
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
     * Checks if this Date falls on a weekend (Saturday or Sunday).
     *
     * @return true if the Date is a weekend, false otherwise
     */
    public boolean isWeekend() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, this.day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }

    /**
     * Checks if this Date is within six months from today.
     *
     * @return true if the Date is within six months, false otherwise
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
     * Determines if this Date is before today's date.
     *
     * @return true if the Date is before today, false otherwise
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
     * Determines if this Date is after today's date.
     *
     * @return true if the Date is after today, false otherwise
     */
    public boolean isAfterToday() {
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
     * Checks if the month of this Date is valid.
     *
     * @return true if the month is valid, false otherwise
     */
    public boolean isValidMonth() {
        if (this.month <= 12 && this.month >= 1) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of days in the given month and year.
     *
     * @param month the month to check
     * @param year  the year to check (for leap years)
     * @return the number of days in the specified month
     */
    public int daysInMonth(int month, int year) {
        int maxDay;
        if (month == 1) {
            maxDay = daysFprMonth[0];
            return maxDay;
        }
        if (month == 2 && isLeapYear(year)) {
            maxDay = 29;
            return maxDay;
        }
        if (month == 2 && !isLeapYear(year)) {
            maxDay = daysFprMonth[1];
            return maxDay;
        }
        if (month == 3) {
            maxDay = daysFprMonth[2];
            return maxDay;
        }
        if (month == 4) {
            maxDay = daysFprMonth[3];
            return maxDay;
        }
        if (month == 5) {
            maxDay = daysFprMonth[4];
            return maxDay;
        }
        if (month == 6) {
            maxDay = daysFprMonth[5];
            return maxDay;
        }
        if (month == 7) {
            maxDay = daysFprMonth[6];
            return maxDay;
        }
        if (month == 8) {
            maxDay = daysFprMonth[7];
            return maxDay;
        }
        if (month == 9) {
            maxDay = daysFprMonth[8];
            return maxDay;
        }
        if (month == 10) {
            maxDay = daysFprMonth[9];
            return maxDay;
        }
        if (month == 11) {
            maxDay = daysFprMonth[10];
            return maxDay;
        }
        if (month == 12) {
            maxDay = daysFprMonth[11];
            return maxDay;
        }
        return -1;
    }

    /**
     * Checks if the given year is a leap year.
     *
     * @param year the year to check
     * @return true if the year is a leap year, false otherwise
     */
    public boolean isLeapYear(int year) {
        if (year % QUATERCENTENNIAL == 0) {
            return true;
        } else if (year % CENTENNIAL == 0) {
            return false;
        } else if (year % QUADRENNIAL == 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the day of this Date is valid for the given month and year.
     *
     * @return true if the day is valid, false otherwise
     */
    public boolean isValidDay() {
        if (this.day > 31) {
            return false;
        }
        int maxMonthDays = daysInMonth(this.month, this.year);
        if (this.day >= 1 && this.day <= maxMonthDays) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the Date is valid based on various criteria: valid day, not today,
     * not before today, within six months, and not a weekend.
     *
     * @return true if the Date is valid, false otherwise
     */
    public boolean isValid() {
        return isValidDay()&& !IsToday() && !isBeforeToday() && withinSix() && !isWeekend();
    }

    /**
     * The main method for testing the Date class functionality.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
            Date test1 = new Date(1989, 12, 32);
            boolean yes = test1.isValid();  // call the instance method
            System.out.println("We can schedule an appointment on this day: " + yes);

            Date test2 = new Date(2024, 9, 30);
            boolean yes1 = test2.isValid();  // call the instance method  should return VALID
            System.out.println("We can schedule an appointment on this day: " + yes1);

            Date test3 = new Date(2025, 1, 13);
            boolean yes3 = test3.isValid();  // call the instance method should return INVALID
            System.out.println("We can schedule an appointment on this day: " + yes3);

            Date test4=new Date(2024,9,29);
            boolean yes4= test4.isValid();  // call the instance method should return INVALID
            System.out.println("We can schedule an appointment on this day: " + yes4);

            Date test5=new Date(2022,2,4);
            boolean yes5=test5.isValid();
            System.out.println("We can schedule an appointment on this day: " + yes5);

            Date test6= new Date(2024,10,11);
            boolean yes6=test6.isValid();
            System.out.println("We can schedule an appointment on this day: " +yes6);
        }
    }


