package softmeth.project2;

import util.Date;

/**
 * Represents a profile containing personal information of a person,
 * including first name, last name, and date of birth.
 * This class implements Comparable to allow profiles to be compared based
 * on their names and date of birth.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Constructs a Profile with the specified first name, last name, and date of birth.
     *
     * @param fname The first name of the individual.
     * @param lname The last name of the individual.
     * @param dob The date of birth of the individual.
     */
    public Profile(String fname, String lname, Date dob){
        this.fname=fname;
        this.lname=lname;
        this.dob=dob;
    }

    /**
     * Gets the last name of the individual.
     *
     * @return The last name of the individual.
     */
    public String getLname(){
        return lname;
    }

    /**
     * Gets the first name of the individual.
     *
     * @return The first name of the individual.
     */
    public String getFname(){
        return fname;
    }

    /**
     * Gets the date of birth of the individual.
     *
     * @return The date of birth as a Date object.
     */
    public Date getDob(){
        return dob;
    }

    /**
     * Indicates whether some other object is "equal to" this Profile.
     * The equality is determined based on the first name, last name, and date of birth.
     *
     * @param patProf The reference object with which to compare.
     * @return true if this Profile is the same as the patProf argument; false otherwise.
     */
    @Override
    public boolean equals(Object patProf){
        if(this==patProf){
            return true;
        }
        if(patProf==null|| !(patProf instanceof Profile)){
            return false; //clearly not equal
        }
        Profile profObject=(Profile) patProf;

        if((fname.equals(profObject.fname))&& (dob.equals(profObject.dob)) && (lname.equals(profObject.lname))){
            return true;
        }
        return false;
    }

    /**
     * Compares this Profile with another Profile for order.
     * The comparison is based on first name, then last name, and finally date of birth.
     *
     * @param profile2 The other Profile to compare with.
     * @return A negative integer, zero, or a positive integer
     *         as this Profile is less than, equal to, or greater than the specified Profile.
     */
    @Override
    public int compareTo(Profile profile2){

        int fNameCompare=fname.compareTo(profile2.fname);
        if((fNameCompare)!=0){
            return fNameCompare < 0 ? -1 : 1;

        }
        int LNameCompare=lname.compareTo(profile2.lname);
        if((LNameCompare)!=0){
            return LNameCompare < 0 ? -1 : 1;


        }
        int dobCompare = dob.compareTo(profile2.dob);
        if (dobCompare != 0) {
            return dobCompare < 0 ? -1 : 1;
        }
        return 0;

    }

    /**
     * Returns a string representation of the Profile.
     * The string includes the first name, last name, and date of birth.
     *
     * @return A string representation of the Profile.
     */
    @Override
    public String toString(){
        return "softmeth.project2.Profile{" +
                "First Name='" + fname + '\'' +
                ",Last Name='" + lname + '\'' +
                ", util.Date of Birth=" + dob +
                '}';
    }

    /**
     * Main method for testing the Profile class.
     * It contains various test cases to check the functionality
     * of equals and compareTo methods.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args){
        //test 1 when they're equal
        Date profile1D=new Date(2020,11,19);
        Date profile2D=new Date(2020,11,19);
        Profile profile1=new Profile("Mary","Smith", profile1D);
        Profile profile2=new Profile("Mary","Smith",profile2D);
        int test1=profile1.compareTo(profile2);
        System.out.println("softmeth.project2.Profile 1 compared to softmeth.project2.Profile 2 "+ test1);

        //test 2 returns -1 (firstName is greater)
        Date profile1F=new Date(2020,11,19);
        Date profile2F=new Date(2020,11,19);
        Profile profileA=new Profile("Amy","Smith", profile1F);
        Profile profileB=new Profile("Bob","Smith",profile2F);
        int test2=profileA.compareTo(profileB);
        System.out.println("softmeth.project2.Profile A compared to softmeth.project2.Profile B "+ test2);

        //test3 returns -1 (lastName is greater)
        Date profileX=new Date(2020,11,19);
        Date profileY=new Date(2020,11,19);
        Profile profile1X=new Profile("Amy","Adam", profileX);
        Profile profile1Y=new Profile("Amy","Smith",profileY);
        int test3=profile1X.compareTo(profile1Y);
        System.out.println("softmeth.project2.Profile 1X compared to softmeth.project2.Profile 1Y "+ test3);

        //test4 returns -1 (dob is earlier)
        Date one=new Date(2020,7,19);
        Date two=new Date(2020,11,19);
        Profile profileD1=new Profile("Amy","Adam", one);
        Profile profileD2=new Profile("Amy","Adam",two);
        int test4=profileD1.compareTo(profileD2);
        System.out.println("softmeth.project2.Profile D1 compared to softmeth.project2.Profile D2 "+ test4);

        //test5 return 1
        Date y=new Date(2020,7,19);
        Date x=new Date(2020,11,19);
        Profile profileC=new Profile("Belle","Smith", y);
        Profile profileD=new Profile("Anne","Smith",x);
        int test5=profileC.compareTo(profileD);
        System.out.println("softmeth.project2.Profile C compared to softmeth.project2.Profile D "+ test5);

        //test6 return 1
        Date cat=new Date(2020,7,19);
        Date dog=new Date(2020,11,19);
        Profile profileCat=new Profile("Belle","Smith", cat);
        Profile profileDog=new Profile("Belle","Adam",dog);
        int test6=profileCat.compareTo(profileDog);
        System.out.println("softmeth.project2.Profile Cat compared to softmeth.project2.Profile Dog "+ test6);

        //test7 return 1
        Date apple=new Date(2020,11,19);
        Date pear=new Date(2020,7,19);
        Profile profileApple=new Profile("Belle","Smith", apple);
        Profile profilePear=new Profile("Belle","Smith",pear);
        int test7=profileApple.compareTo(profilePear);
        System.out.println("softmeth.project2.Profile Apple compared to softmeth.project2.Profile Pear "+ test7);
    }

}

