public class Visit {
    private Appointment appointment; //a reference to the appointment object
    private Visit next; //a ref. to the next appointment object in the list
    //visit is to mark a completed appointment, its like a record of all appointments

    public Visit(Appointment appointment){
        this.appointment=appointment;
        this.next=null;


    }

    public Appointment getAppointment(){
        return appointment;
    }

    public Visit getNext(){
        return next;
    }

    public void setNext(Visit next){
        this.next=next;
    }


}
