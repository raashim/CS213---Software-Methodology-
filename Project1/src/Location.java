/**
 * This enum represents various locations where appointments can be scheduled.
 * Each location has an associated county and zip code.
 * It provides methods to retrieve the county and zip code for each location.
 */
public enum Location {
    Bridgewater("Somerset", "08807"),
    Edison("Middlesex", "08817"),
    Piscataway("Middlesex", "08854"),
    Princeton("Mercer", "08542"),
    Morristown("Morris", "07960"),
    Clark("Union", "07066");

    private final String county;
    private final String zip;


    /**
     * Constructor for the Location enum.
     * Initializes the county and zip code for each location.
     *
     * @param county The county in which the location is located.
     * @param zip The zip code of the location.
     */
    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    /**
     * Gets the county of the location.
     *
     * @return The county as a String.
     */
    public String getCounty() {
        return this.county;
    }

    /**
     * Gets the zip code of the location.
     *
     * @return The zip code as a String.
     */
    public String getZip() {
        return this.zip;
    }
}
