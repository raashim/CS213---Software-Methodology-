package softmeth.project2;

/**
 * Represents a technician, which is a type of provider with a specific rate per visit.
 * This class extends the Provider class and adds functionality specific to technicians.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class Technician extends Provider {

    private int ratePerVisit;

    /**
     * Constructs a Technician object with a specified rate per visit, profile, and location.
     *
     * @param ratePerVisit The rate charged by the technician per visit.
     * @param profile      The profile of the technician.
     * @param location     The location of the technician.
     */
    public Technician(int ratePerVisit, Profile profile, Location location) {
        super(profile, location);
        this.ratePerVisit = ratePerVisit;
    }

    /**
     * Gets the rate per visit for this technician.
     *
     * @return The rate charged by the technician per visit.
     */
    public int getRatePerVisit() {
        return ratePerVisit;
    }

    /**
     * Returns the rate charged per visit.
     *
     * @return The rate per visit.
     */
    public int rate() {
        return ratePerVisit;
    }

    /**
     * Sets the rate per visit for this technician.
     *
     * @param ratePerVisit The new rate to be set.
     */
    public void setRatePerVisit(int ratePerVisit) {
        this.ratePerVisit = ratePerVisit;
    }

    /**
     * Compares this technician to another object for equality.
     *
     * @param second The object to compare to this technician.
     * @return true if this technician is equal to the specified object; false otherwise.
     */
    @Override
    public boolean equals(Object second) {
        if (this == second) {
            return true;
        }
        if (second == null || getClass() != second.getClass()) {
            return false;
        }
        Technician tech = (Technician) second;
        return ratePerVisit == tech.ratePerVisit && super.equals(second);
    }

    /**
     * Compares this technician with another person based on their rate per visit.
     *
     * @param other The person to compare with.
     * @return A negative integer, zero, or a positive integer as this technician's rate
     * is less than, equal to, or greater than the specified person's rate.
     */
    @Override
    public int compareTo(Person other) {
        if (other instanceof Technician) {
            Technician technician2 = (Technician) other;
            return Integer.compare(this.ratePerVisit, technician2.ratePerVisit);
        }
        return super.compareTo(other);
    }
}
