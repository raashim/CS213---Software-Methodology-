package softmeth.project2;

/**
 * Represents the different specialties of technicians with their associated charges.
 * This enum defines specific specialties and their corresponding charges per visit.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public enum Specialty {
    Family(250),
    Pediatrician(300),
    Allergist(350);

    private final int charge;

    /**
     * Constructs a Specialty enum constant with the specified charge.
     *
     * @param charge The charge associated with the specialty.
     */
    Specialty(int charge) {
        this.charge = charge;
    }

    /**
     * Gets the charge associated with this specialty.
     *
     * @return The charge for this specialty.
     */
    public int getCharge(){
        return this.charge;
    }

}
