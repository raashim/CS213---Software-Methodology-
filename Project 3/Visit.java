package com.example.uiruclinic.src.softmeth.project2;

/**
 * Represents a completed visit associated with an appointment.
 * This class is used to create a linked list of completed appointments.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarthy
 */
public class Visit {
    private Appointment appointment;
    private Visit next;

    /**
     * Constructor for the softmeth.project2.Visit class.
     *
     * @param appointment The appointment object associated with this visit.
     */
    public Visit(Appointment appointment){
        this.appointment=appointment;
        this.next=null;
    }

    /**
     * Gets the appointment associated with this visit.
     *
     * @return The softmeth.project2.Appointment object for this visit.
     */
    public Appointment getAppointment(){
        return appointment;
    }

    /**
     * Gets the next softmeth.project2.Visit object in the linked list.
     *
     * @return The next softmeth.project2.Visit object, or null if there is no next visit.
     */
    public Visit getNext(){
        return next;
    }

    /**
     * Sets the next softmeth.project2.Visit object in the linked list.
     *
     * @param next The softmeth.project2.Visit object to set as the next appointment in the list.
     */
    public void setNext(Visit next){
        this.next=next;
    }
}
