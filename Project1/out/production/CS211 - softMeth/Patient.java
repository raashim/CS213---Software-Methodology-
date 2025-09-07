public class Patient implements Comparable<Patient>{
    private Profile profile;
    int charge=0;
    private Visit visits; // a linked list of visits (completed appts)

    public Patient(Profile profile, Visit visits){
        this.profile=profile;
        this.visits=visits;

    }

    @Override
    public int compareTo(Patient patient2){
        return profile.compareTo(patient2.getProfile());
    }

    @Override
    public boolean equals(Object patient2){
        if(this==patient2){
            return true;
        }
        if(patient2==null|| !(patient2 instanceof Patient)){
            return false; //clearly not equal
        }
        Patient patientObject = (Patient) patient2;

        if((charge == (patientObject.charge))&& (profile.equals(patientObject.getProfile()))){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "Charge ='" + charge + '\'' +
                ", Profile ='" + profile + '\'' +
                '}';
    }

    //adding to visits
    public void addAVisit(Appointment appointment){
        Visit newOne=new Visit(appointment);
        newOne.setNext(visits);
        visits=newOne;
    }

    public Profile getProfile(){
        return profile;
    }
    public Visit getVisits(){
        return visits;
    }


    public int charge(){//traverses the linked list to compute the charge
        int charge=0;
        Visit ptr=visits;
        while(ptr!=null){
            charge+=ptr.getAppointment().getProvider().getSpecialty().getCharge();
            ptr=ptr.getNext();
        }
        return charge;
    }
}
