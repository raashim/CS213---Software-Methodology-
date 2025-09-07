/**
 * This class represents a patient's profile, including first name, last name, and date of birth.
 * It implements the Comparable interface to allow comparison between profiles based on these attributes.
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Constructor to initialize the Profile object with first name, last name, and date of birth.
     *
     * @param fname The first name of the patient.
     * @param lname The last name of the patient.
     * @param dob The date of birth of the patient.
     */
    public Profile(String fname, String lname, Date dob){
        this.fname=fname;
        this.lname=lname;
        this.dob=dob;
    }

    /**
     * Returns the last name of the patient.
     *
     * @return The last name.
     */
    public String getLname(){
        return lname;
    }

    /**
     * Returns the first name of the patient.
     *
     * @return The first name.
     */
    public String getFname(){
        return fname;
    }

    /**
     * Returns the date of birth of the patient.
     *
     * @return The date of birth.
     */
    public Date getDob(){
        return dob;
    }

    /**
     * Checks if this profile is equal to another object.
     * Two profiles are considered equal if they have the same first name, last name, and date of birth.
     *
     * @param patProf The object to compare with.
     * @return True if the profiles are equal, false otherwise.
     */
    @Override
    public boolean equals(Object patProf){
        if(this==patProf){
            return true;
        }
        if(patProf==null|| !(patProf instanceof Profile)){
            return false; //clearly not equal
        }
        Profile profObject=(Profile) patProf;

        if((fname.equals(profObject.fname))&& (dob.equals(profObject.dob)) && (lname.equals(profObject.lname))){
            return true;
        }
        return false;
    }

    /**
     * Compares this profile to another profile based on first name, last name, and date of birth.
     * The comparison is done in the following order: first name, last name, then date of birth.
     *
     * @param profile2 The profile to compare against.
     * @return A negative, zero, or positive integer based on the comparison.
     */
    @Override
        public int compareTo(Profile profile2){

            int fNameCompare=fname.compareTo(profile2.fname);
            if((fNameCompare)!=0){
                return fNameCompare < 0 ? -1 : 1;

            }
            int LNameCompare=lname.compareTo(profile2.lname);
            if((LNameCompare)!=0){
                return LNameCompare < 0 ? -1 : 1;


            }
            int dobCompare = dob.compareTo(profile2.dob);
            if (dobCompare != 0) {
                return dobCompare < 0 ? -1 : 1;
            }
            return 0;

        }

    /**
     * Returns a string representation of the Profile object.
     *
     * @return A string with the first name, last name, and date of birth.
     */
    @Override
    public String toString(){
        return "Profile{" +
                "First Name='" + fname + '\'' +
                ",Last Name='" + lname + '\'' +
                ", Date of Birth=" + dob +
                '}';
    }

    /**
     * A main method to test the comparison of different Profile objects.
     * It runs various tests to check if the compareTo method functions as expected.
     */
    public static void main(String[] args){
        Date profile1D=new Date(2020,11,19);
        Date profile2D=new Date(2020,11,19);
        Profile profile1=new Profile("Mary","Smith", profile1D);
        Profile profile2=new Profile("Mary","Smith",profile2D);
        int test1=profile1.compareTo(profile2);
        System.out.println("Profile 1 compared to Profile 2"+ test1);

        Date profile1F=new Date(2020,11,19);
        Date profile2F=new Date(2020,11,19);
        Profile profileA=new Profile("Amy","Smith", profile1F);
        Profile profileB=new Profile("Bob","Smith",profile2F);
        int test2=profileA.compareTo(profileB);
        System.out.println("Profile A compared to Profile B"+ test2);

        Date profileX=new Date(2020,11,19);
        Date profileY=new Date(2020,11,19);
        Profile profile1X=new Profile("Amy","Adam", profileX);
        Profile profile1Y=new Profile("Amy","Smith",profileY);
        int test3=profile1X.compareTo(profile1Y);
        System.out.println("Profile 1X compared to Profile 1Y "+ test3);

        Date one=new Date(2020,7,19);
        Date two=new Date(2020,11,19);
        Profile profileD1=new Profile("Amy","Adam", one);
        Profile profileD2=new Profile("Amy","Adam",two);
        int test4=profileD1.compareTo(profileD2);
        System.out.println("Profile D1 compared to Profile D2 "+ test4);

        Date y=new Date(2020,7,19);
        Date x=new Date(2020,11,19);
        Profile profileC=new Profile("Belle","Smith", y);
        Profile profileD=new Profile("Anne","Smith",x);
        int test5=profileC.compareTo(profileD);
        System.out.println("Profile C compared to Profile D "+ test5);

        Date cat=new Date(2020,7,19);
        Date dog=new Date(2020,11,19);
        Profile profileCat=new Profile("Belle","Smith", cat);
        Profile profileDog=new Profile("Belle","Adam",dog);
        int test6=profileCat.compareTo(profileDog);
        System.out.println("Profile Cat compared to Profile Dog "+ test6);

        Date apple=new Date(2020,11,19);
        Date pear=new Date(2020,7,19);
        Profile profileApple=new Profile("Belle","Smith", apple);
        Profile profilePear=new Profile("Belle","Smith",pear);
        int test7=profileApple.compareTo(profilePear);
        System.out.println("Profile Apple compared to Profile Pear "+ 7);
    }

}
