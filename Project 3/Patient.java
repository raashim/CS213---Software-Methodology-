package com.example.uiruclinic.src.softmeth.project2;

/**
 * Represents a patient in the healthcare system.
 * This class extends the Person class and includes specific details
 * related to a patient's visits.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarthy
 */
public class Patient extends Person {
    private Visit visit;

    /**
     * Constructs a Patient with the specified visit and profile.
     *
     * @param visit The visit associated with this patient.
     * @param profile The profile containing personal information of the patient.
     */
    public Patient(Visit visit, Profile profile ){
        super(profile);
        this.visit=visit;
    }

    /**
     * Compares this Patient with another Person for order.
     * The comparison is based on the profiles of both Patients.
     * If the other Person is not a Patient, the comparison is still based on profiles.
     *
     * @param two The other Person to compare with.
     * @return A negative integer, zero, or a positive integer
     *         as this Patient's profile is less than, equal to,
     *         or greater than the specified Person's profile.
     */
        @Override
        public int compareTo(Person two) {

            if (two instanceof Patient) {
                Patient otherP = (Patient) two;
                return super.compareTo(two);
            }
            return super.compareTo(two);
        }

    /**
     * Indicates whether some other object is "equal to" this Patient.
     * Equality is determined based on the profile of each Patient.
     *
     * @param two The reference object with which to compare.
     * @return true if this Patient is the same as the two argument; false otherwise.
     */
        @Override
        public boolean equals(Object two) {
            if (this == two) {
                return true;
            }
            if (two == null || getClass() != two.getClass()) {
                return false;
            }
            return super.equals(two);
        }

    /**
     * Returns a string representation of the Patient.
     * The string includes the profile data associated with this Patient.
     *
     * @return A string representation of the Patient.
     */
        @Override
        public String toString() {
            return "softmeth.project2.Patient{" +
                    ", profile=" + getProfile() +
                    '}';
        }
    }