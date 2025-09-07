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

    Provider(Location location, Specialty specialty) {
        this.location = location;
        this.specialty = specialty;
    }

    public Location getLocation() {
        return this.location;
    }

    public Specialty getSpecialty(){
        return this.specialty;
    }
}
