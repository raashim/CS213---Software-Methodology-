package com.example.uiruclinic.src.softmeth.project2;

import com.example.uiruclinic.src.util.Date;

/**
 * Represents a person with a profile containing personal information.
 * This class implements Comparable to allow Person objects to be compared
 * based on their profiles.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarthy
 */
public class Person implements Comparable<Person>{

    protected Profile profile;

    /**
     * Constructs a Person with the specified profile.
     *
     * @param profile The profile containing personal information.
     */
    public Person(Profile profile){
        this.profile=profile;
    }

    /**
     * Gets the profile of the person.
     *
     * @return The Profile object associated with this Person.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Gets the location of the person if they are a Provider.
     * This method will return null for non-Provider instances.
     *
     * @return The Location object associated with this Provider, or null if not applicable.
     */
    public Location getLocation() {
        if(this instanceof Provider) {
            return this.getLocation();
        }
        return null;
    }

    /**
     * Compares this Person with another Person for order.
     * The comparison is based on the profiles of both Persons.
     *
     * @param second The other Person to compare with.
     * @return A negative integer, zero, or a positive integer
     *         as this Person's profile is less than, equal to,
     *         or greater than the specified Person's profile.
     */
    @Override
    public int compareTo(Person second){
        return profile.compareTo(second.getProfile());
    }

    /**
     * Indicates whether some other object is "equal to" this Person.
     * Equality is determined based on the profile of each Person.
     *
     * @param two The reference object with which to compare.
     * @return true if this Person is the same as the two argument; false otherwise.
     */
    @Override
    public boolean equals(Object two){
        if (this==two){ //if this person equals another
            return true;
        }
        if(two==null||!(two instanceof Person)){
            return false;
        }
        Person second=(Person)two; //casts o
        return profile.equals(second.getProfile());
    }

    /**
     * Returns a string representation of the Person.
     * The string includes the Profile data associated with this Person.
     *
     * @return A string representation of the Person.
     */
    @Override
    public String toString(){
        return "softmeth.project2.Person{"+
                "softmeth.project2.Profile data:"+profile+ '}';
    }

    /**
     * Gets the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLname() {
        return profile.getLname();
    }

    /**
     * Gets the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getFname() {
        return profile.getFname();
    }

    /**
     * Gets the date of birth of the person.
     *
     * @return The date of birth as a Date object.
     */
    public Date getDob() {
        return profile.getDob();
    }
}