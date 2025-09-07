package com.example.uiruclinic.src.softmeth.project2;

import com.example.uiruclinic.src.util.Date;

/**
 * Represents a profile containing personal information of a person,
 * including first name, last name, and date of birth.
 * This class implements Comparable to allow profiles to be compared based
 * on their names and date of birth.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarthy
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
}

