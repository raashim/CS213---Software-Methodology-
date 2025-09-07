package util;


import org.junit.Test;
import softmeth.project2.*;


import static org.junit.Assert.*;

/**
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class ListTest {
    @Test
    public void addDoctor() {
        List<Provider> providers = new List<>();


        Date date1 = new Date(2003, 10, 20);
        Profile profile1 = new Profile("Lucy", "Doe", date1);
        Doctor doctor = new Doctor(profile1, Location.Bridgewater, Specialty.Allergist, "53");


        providers.add(doctor);
        assertTrue(providers.contains(doctor));
    }




    @Test
    public void addTech() {
        List<Provider> providers = new List<>();


        Date date3 = new Date(1994, 8, 24); // August 24, 1984
        Profile profile5 = new Profile("Milla", "Lim", date3);
        Technician tech = new Technician(150, profile5, Location.Edison);


        providers.add(tech);
        assertTrue(providers.contains(tech));
    }




    @Test
    public void removeDoctor() {
        List<Provider> providers = new List<>();




        Date date1 = new Date(2003, 10, 20);
        Profile profile1 = new Profile("Lucy", "Doe", date1);
        Doctor doctor = new Doctor(profile1, Location.Bridgewater, Specialty.Allergist, "53");




        providers.add(doctor);
        providers.remove(doctor);
        assertFalse(providers.contains(doctor));
    }




    @Test
    public void removeTech() {
        List<Provider> providers = new List<>();




        Date date3 = new Date(1994, 8, 24);
        Profile profile5 = new Profile("Milla", "Lim", date3);
        Technician tech = new Technician(150, profile5, Location.Edison);




        providers.add(tech);
        providers.remove(tech);
        assertFalse(providers.contains(tech));
    }
}



