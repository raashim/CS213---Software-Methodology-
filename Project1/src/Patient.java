/**
 * This class represents a patient, including their profile, charge amount,
 * and a linked list of visits (completed appointments).
 * It implements the Comparable interface to allow comparison between patients based on their profiles.
 */
public class Patient implements Comparable<Patient>{
    private Profile profile;
    int charge=0;
    private Visit visits;

    /**
     * Constructor to initialize the Patient object with a profile and a list of visits.
     *
     * @param profile The patient's profile information.
     * @param visits A linked list of completed visits.
     */
    public Patient(Profile profile, Visit visits){
        this.profile=profile;
        this.visits=visits;

    }

    /**
     * Compares this patient to another patient based on their profile.
     *
     * @param patient2 The patient to compare against.
     * @return An integer representing the comparison result (based on Profile comparison).
     */
    @Override
    public int compareTo(Patient patient2){
        return profile.compareTo(patient2.getProfile());
    }

    /**
     * Checks if this patient is equal to another object.
     * Two patients are considered equal if they have the same profile and charge.
     *
     * @param patient2 The object to compare with.
     * @return True if the patients are equal, false otherwise.
     */
    @Override
    public boolean equals(Object patient2){
        if(this==patient2){
            return true;
        }
        if(patient2==null|| !(patient2 instanceof Patient)){
            return false;
        }
        Patient patientObject = (Patient) patient2;

        if((charge == (patientObject.charge))&& (profile.equals(patientObject.getProfile()))){
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the Patient object.
     *
     * @return A string with patient charge and profile.
     */
    @Override
    public String toString() {
        return "Patient{" +
                "Charge ='" + charge + '\'' +
                ", Profile ='" + profile + '\'' +
                '}';
    }

    /**
     * Adds a new visit to the patient's linked list of visits.
     *
     * @param appointment The appointment to add as a new visit.
     */
    public void addAVisit(Appointment appointment){
        Visit newOne=new Visit(appointment);
        newOne.setNext(visits);
        visits=newOne;
    }

    /**
     * Returns the patient's profile.
     *
     * @return The profile of the patient.
     */
    public Profile getProfile(){
        return profile;
    }

    /**
     * Returns the linked list of the patient's visits.
     *
     * @return The visits linked list.
     */
    public Visit getVisits(){
        return visits;
    }

    /**
     * Calculates the total charge by traversing the linked list of visits.
     * Each visit has a charge based on the specialty of the provider.
     *
     * @return The total charge of all visits.
     */
    public int charge(){//traverses the linked list to compute the charge
        int charge=0;
        Visit ptr=visits;
        while(ptr!=null){
            charge+=ptr.getAppointment().getProvider().getSpecialty().getCharge();
            ptr=ptr.getNext();
        }
        return charge;
    }
}
