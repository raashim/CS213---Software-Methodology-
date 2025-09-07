public enum Location {
    Bridgewater("Somerset", "08888"),
    Edison("Middlesex", "08817"),
    Piscataway("Middlesex", "08817"),
    Princeton("Mercer", "08542"),
    Morristown("Morris", "07960"),
    Clark("Union", "07066");

    private final String county;
    private final String zip;

    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    public String getCounty() {
        return this.county;
    }

    public String getZip(){
        return this.zip;
    }
}
