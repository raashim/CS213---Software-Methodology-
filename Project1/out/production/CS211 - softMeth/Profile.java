public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;


    public Profile(String fname, String lname, Date dob){
        this.fname=fname;
        this.lname=lname;
        this.dob=dob;
    }

    public String getLname(){
        return lname;
    }
    public String getFname(){
        return fname;
    }
    public Date getDob(){
        return dob;
    }

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


    @Override
    public int compareTo(Profile profile2){

        int fNameCompare=fname.compareTo(profile2.fname);
        if((fNameCompare)!=0){
            return fNameCompare;

        }
        int LNameCompare=fname.compareTo(profile2.lname);
        if((LNameCompare)!=0){
            return LNameCompare;

        }
        return dob.compareTo(profile2.dob);

    }

    @Override
    public String toString(){
        return "Profile{" +
                "First Name='" + fname + '\'' +
                ",Last Name='" + lname + '\'' +
                ", Date of Birth=" + dob +
                '}';
    }

}
