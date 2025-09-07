package util;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class DateTest {


    @Test
    public void isValidDay() {
        Date testOne = new Date(2004, 3, 60);
        assertFalse(testOne.isValid());
    }




    @Test
    public void isValidCalendarDate() {
        Date testTwo = new Date(2024, 9, 22);
        assertFalse(testTwo.isValid());
    }




    @Test
    public void isWeekendDay() {
        Date testThree = new Date(2024, 11, 9);
        assertFalse(testThree.isValid());
    }




    @Test
    public void isValidBefore() { //befrore today
        Date testFive = new Date(2022, 2, 4);
        assertFalse(testFive.isValid());
    }




    @Test
    public void isValidMonthDay() {
        Date testFour = new Date(2024, 12, 31);
        assertTrue(testFour.isValid());
    }




    @Test
    public void isMoreThanSixMonths() {
        Date testSix = new Date(2026, 11, 10);
        assertFalse(testSix.isValid());
    }


}

