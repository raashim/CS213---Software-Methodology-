public class List {
    private Appointment[] appointments;
    private int size; //number of appointments in the array

    public List () {
        size = 0;
        appointments = new Appointment[4];
    }

    public int getSize(){
        return size;
    }

    public boolean contains(Appointment appointment) {
        int num = find(appointment);
        if (num != -1) {
            return true;
        }
        return false;
    }

    //instead of the .contains method, we could end up using the find method
    public void add(Appointment appointment){
        if(!contains(appointment))
        {

            int n = appointments.length;
            if(appointments[n - 1] != null) {
                grow();
            }
            for(int i=0;i<n;i++){
                if(appointments[i]==null){
                    appointments[i]=appointment;
                    size++;
                    break; //breaks after finding the first empty slot to add it into
                }
            }
        }
    }

    //instead of the .contains method, we could end up using the find method
   public void remove(Appointment appointment){
       if(contains(appointment)){
           int num=find(appointment);
           appointments[num]=null;
       }
   }

    private void grow() {
        //goes through appointment, sees if last element is not null and resizes
        int size = appointments.length;

        Appointment[] newAppt = new Appointment[size + 4];
        //copies old elements into new elements
        for (int i = 0; i < size; i++) {
            newAppt[i] = appointments[i];
        }
        appointments = newAppt; //stores new array into old array
    }

    private int find(Appointment appointment) {//helper method
        int NOT_FOUND = -1;


            for (int i = 0; i < appointments.length; i++) {
                if (appointments[i] != null && appointments[i].compareTo(appointment) == 0) {
                    return i;
                }
        }
            return NOT_FOUND;
    }

    public void printByPatient() {

        //ordered by patient profile, date, timeslot
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].compareTo(appointments[j + 1]) > 0) {

                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        //actually print them so that you can call the method in the scheduler class
        for(int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
    }

    public void printByLocation() {

        //ordered by county, date, and then timeslot
        for(int i = 0; i < size - 1; i++) {
            for(int j = 0; j < size - i - 1; j++) {
                if(appointments[j].compareByLocation(appointments[j + 1]) > 0) {

                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        //actually print them so that you can call the method in the scheduler class
        for(int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
    }

    public void printByAppointment () {

        //ordered by date, then timeslot, and then provider name
        for(int i = 0; i < size - 1; i++) {
            for(int j = 0; j < size - i - 1; j++) {
                if(appointments[j].compareByAppointment(appointments[j + 1]) > 0) {

                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                }
            }
        }

        //actually print them so that you can call the method in the scheduler class
        for(int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
    }

}

