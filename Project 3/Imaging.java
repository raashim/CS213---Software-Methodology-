package com.example.uiruclinic.src.softmeth.project2;

import com.example.uiruclinic.src.util.Date;

/**
 * Represents an imaging appointment which includes details about the date, timeslot,
 * patient, provider, and the specific radiology room used for the imaging.
 * @author Raashi Maheshwari
 * @author Tanvi Yamarthy
 */
public class Imaging extends Appointment {
    private Radiology room;

    /**
     * Constructor for the Imaging class.
     *
     * @param date The date of the imaging appointment.
     * @param timeslot The timeslot of the imaging appointment.
     * @param patient The patient associated with the imaging appointment.
     * @param provider The provider associated with the imaging appointment.
     * @param room The radiology room used for the imaging.
     */
    public Imaging(Date date, Timeslot timeslot, Person patient, Person provider, Radiology room) {
        super(date, timeslot, patient, provider);
        this.date=date;
        this.timeslot=timeslot;
        this.patient=patient;
        this.provider=provider;
        this.room=room;
    }

    /**
     * Gets the radiology room associated with this imaging appointment.
     *
     * @return The Radiology enum representing the room for the imaging appointment.
     */
    public Radiology getRoom() {
        return room;
    }

    /**
     * Sets the radiology room for this imaging appointment.
     *
     * @param room The Radiology enum representing the room to set for the imaging appointment.
     */
    public void setRoom(Radiology room){
        this.room=room;
    }
}
