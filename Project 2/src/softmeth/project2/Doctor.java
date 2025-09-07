package softmeth.project2;

/**
 * Represents a doctor in the healthcare system, extending the Provider class.
 * This class encapsulates the doctor's profile, location, specialty, and National Provider Identifier (NPI).
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class Doctor extends Provider {

    private Specialty specialty;
    private String npi;

    /**
     * Constructor for the Doctor class.
     *
     * @param profile The profile of the doctor containing personal information.
     * @param location The location where the doctor practices.
     * @param specialty The specialty of the doctor.
     * @param npi The National Provider Identifier of the doctor.
     */
    public Doctor(Profile profile, Location location, Specialty specialty, String npi){
        super(profile,location);
        this.specialty=specialty;
        this.npi=npi;
    }

    /**
     * Gets the National Provider Identifier (NPI) of the doctor.
     *
     * @return The NPI as a String.
     */
    public String getNpi(){
        return npi;
    }

    /**
     * Gets the specialty of the doctor.
     *
     * @return The Specialty enum representing the doctor's specialty.
     */
    public Specialty getSpecialty(){
        return specialty;
    }

    /**
     * Overrides the rate method from the Provider class to return the charge rate based on the doctor's specialty.
     *
     * @return The charge rate for the doctor's services based on their specialty.
     */
    @Override
    public int rate(){
        return specialty.getCharge();
    }

    /**
     * Overrides the toString method to provide a string representation of the doctor's information.
     *
     * @return A formatted string containing the doctor's name, date of birth, location, specialty, and NPI.
     */
    @Override
    public String toString(){
        return String.format("[%s %s %s, %s, %s %s][%s, #%s]",
                profile.getFname(),
                profile.getLname(),
                profile.getDob(),
                getLocation().toString().toUpperCase(),
                getLocation().getCounty(),
                getLocation().getZip(),
                getSpecialty().toString().toUpperCase(),
                getNpi());
    }
}
