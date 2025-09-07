/**
 * Enum representing different healthcare providers.
 * Each provider is associated with a specific location and medical specialty.
 */
public enum Provider {
    PATEL(Location.Bridgewater, Specialty.Family),
    LIM(Location.Bridgewater, Specialty.Pediatrician),
    ZIMNES(Location.Clark, Specialty.Family),
    HARPER(Location.Clark, Specialty.Family),
    KAUR(Location.Princeton, Specialty.Allergist),
    TAYLOR(Location.Piscataway, Specialty.Pediatrician),
    RAMESH(Location.Morristown, Specialty.Allergist),
    CERAVOLO(Location.Edison, Specialty.Pediatrician);


    private final Location location;
    private final Specialty specialty;
    /**
     * Constructor for the Provider enum.
     * Each provider is associated with a specific location and specialty.
     *
     * @param location The location of the provider.
     * @param specialty The medical specialty of the provider.
     */
    Provider(Location location, Specialty specialty) {
        this.location = location;
        this.specialty = specialty;
    }

    /**
     * Returns the location where the provider practices.
     *
     * @return The location of the provider.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Returns the medical specialty of the provider.
     *
     * @return The specialty of the provider.
     */
    public Specialty getSpecialty(){
        return this.specialty;
    }


}
