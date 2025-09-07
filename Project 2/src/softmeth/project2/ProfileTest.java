package softmeth.project2;


import org.junit.Test;
import util.Date;


import static org.junit.Assert.*;

/**
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class ProfileTest {


    @Test
    public void compareOne() {
        Date date1 = new Date(2003, 11, 20);
        Profile profile1 = new Profile("Lucy", "Smith", date1);
        Profile profile2 = new Profile("Lucy", "Smith", date1);
        assertEquals(0, profile1.compareTo(profile2));
    }




    @Test
    public void compareTwo() {
        Date date2 = new Date(1997, 1, 18);
        Profile profile3 = new Profile("John", "Doe", date2);
        Profile profile4 = new Profile("Jona", "Roew", date2);
        assertEquals(-1, profile3.compareTo(profile4));
    }




    @Test
    public void compareThree() {
        Date date3 = new Date(1984, 7, 24);
        Profile profile5 = new Profile("David", "Min", date3);
        Profile profile6 = new Profile("Ruby", "Min", date3);
        assertEquals(-1, profile5.compareTo(profile6));
    }




    @Test
    public void compareFour() {
        Date date4 = new Date (2011, 8, 23);
        Date date5 = new Date (2012, 9, 18);
        Profile profile7 = new Profile("Jenna", "Lee", date4);
        Profile profile8 = new Profile("Jenna", "Lee", date5);
        assertEquals(-1, profile7.compareTo(profile8));
    }




    @Test
    public void compareFive() {
        Date date2 = new Date(1997, 1, 18);
        Date date6 = new Date(1994, 1, 30);
        Profile profile9 = new Profile("Zack", "Kase", date2);
        Profile profile10 = new Profile("Rebba", "Dim", date6);
        assertEquals(1, profile9.compareTo(profile10));
    }




    @Test
    public void compareSix() {
        Date date7 = new Date(1950, 6, 5);
        Profile profile12 = new Profile("Chloe", "Banna", date7);
        Profile profile11 = new Profile("Peter", "Hary", date7);
        assertEquals(1, profile11.compareTo(profile12));
    }




    @Test
    public void compareSeven() {
        Date date8 = new Date (2009, 8, 23);
        Date date9 = new Date (2008, 9, 18);
        Profile profile13 = new Profile("Jeff", "Brian", date8);
        Profile profile14 = new Profile("Jeff", "Brian", date9);
        assertEquals(1, profile13.compareTo(profile14));
    }
}
