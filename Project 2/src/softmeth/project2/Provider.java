package softmeth.project2;

/**
 * Represents a provider of healthcare services, such as doctors or technicians.
 * This class is an abstract representation that extends the Person class and
 * provides common attributes and methods for its subclasses.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public abstract class Provider extends Person {
    private final Location location;

    /**
     * Constructs a Provider with the specified profile and location.
     *
     * @param profile The profile of the provider.
     * @param location The location of the provider.
     */
    public Provider(Profile profile, Location location) {
        super(profile);
        this.location = location;
        this.profile = profile;
    }

    /**
     * Gets the profile of this provider.
     *
     * @return The profile associated with this provider.
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Gets the location of this provider.
     *
     * @return The location associated with this provider.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Abstract method to get the rate of the provider.
     *
     * @return The rate charged by the provider per visit.
     */
    public abstract int rate();

    /**
     * Compares this Provider with another Person for order.
     * Comparison is based first on the Provider's profile,
     * and if they are equal, then on their location.
     *
     * @param other The other Person to compare to.
     * @return A negative integer, zero, or a positive integer
     *         as this Provider is less than, equal to, or greater than the specified Person.
     */
    @Override
    public int compareTo(Person other) {
        if (other instanceof Provider) {
            Provider provider2 = (Provider) other;
            int compareProfile = this.getProfile().compareTo(provider2.getProfile());
            if (compareProfile != 0) {
                return compareProfile;
            }
            return this.location.compareTo(provider2.location);
        }
        return super.compareTo(other);
    }

    /**
     * Indicates whether some other object is "equal to" this Provider.
     * The equality is determined based on the Provider's profile and location.
     *
     * @param obj The reference object with which to compare.
     * @return true if this Provider is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Provider)) {
            return false;
        }
        Provider other = (Provider) obj;
        boolean ans = this.getProfile().equals(other.getProfile()) && this.location.equals(other.location);
        return ans;
    }

    /**
     * Returns a string representation of the Provider.
     * The string includes the Provider's name, date of birth, location,
     * and any specific details based on the Provider's type (Doctor or Technician).
     *
     * @return A string representation of the Provider.
     * @throws ClassCastException If the Provider is not a recognized subclass.
     */
    @Override
    public String toString() {
        if (this instanceof Doctor) {
            Doctor doctor = (Doctor) this;
            return "[" + doctor.getFname() + " " + doctor.getLname() + " " + doctor.getDob() + ", " + this.location + ", " + this.location.getCounty() + " " + this.location.getZip() + "][NPI: $" + doctor.getNpi() + "]";


        } else if (this instanceof Technician) {
            Technician tech = (Technician) this;
            return "[" + tech.getFname() + " " + tech.getLname() + " " + tech.getDob() + ", " + this.location.toString().toUpperCase() + ", " + this.location.getCounty() + ", " + this.location.getZip() + "] [rate: $" + tech.getRatePerVisit() + ".00]";
            }
        throw new ClassCastException("Cannot perform toString() on a non-Person object.");
    }
}

