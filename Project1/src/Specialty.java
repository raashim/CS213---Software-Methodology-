/**
 * Enum representing different medical specialties.
 * Each specialty has an associated charge for services.
 */
public enum Specialty {
    Family(250),
    Pediatrician(300),
    Allergist(350);

    private final int charge;

    /**
     * Constructor for Specialty enum.
     *
     * @param charge The charge associated with the specialty.
     */
    Specialty(int charge) {
        this.charge = charge;
    }

    /**
     * Gets the charge for this specialty.
     *
     * @return The charge as an integer.
     */
    public int getCharge(){
        return this.charge;
    }
}
