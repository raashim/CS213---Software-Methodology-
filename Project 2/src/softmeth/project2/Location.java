package softmeth.project2;

/**
 * Represents different locations along with their corresponding counties and zip codes.
 * Each enum constant contains information about a specific location.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
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
     *
     * @param county The county where the location is situated.
     * @param zip The zip code associated with the location.
     */
    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    /**
     * Gets the county associated with this location.
     *
     * @return The county of the location.
     */
    public String getCounty() {
        return this.county;
    }

    /**
     * Gets the zip code associated with this location.
     *
     * @return The zip code of the location.
     */
    public String getZip(){
        return this.zip;
    }

}

