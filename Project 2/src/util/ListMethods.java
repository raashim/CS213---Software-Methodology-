package util;

import softmeth.project2.Appointment;
import softmeth.project2.Profile;
import softmeth.project2.Provider;
import softmeth.project2.Timeslot;

/**
 * A utility class that provides methods for working with lists of generic elements, specifically handling
 * appointments, timeslots, and formatting.
 *
 * @param <E> the type of elements in the list
 *
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class ListMethods<E> extends List {
    private int NOT_FOUND = -1;

    /**
     * Identifies the index of an appointment in the list based on profile, date, and timeslot.
     *
     * @param profile The profile of the person associated with the appointment.
     * @param date The date of the appointment.
     * @param timeslot The timeslot of the appointment.
     * @param appList The list of elements (appointments) to search through.
     * @return The index of the appointment in the list if found, or NOT_FOUND (-1) if not found.
     */
    public int identifyAppointment(Profile profile, Date date, Timeslot timeslot, List<E> appList) {
        for (int i = 0; i < appList.size(); i++) {

            if (appList.get(i) instanceof Appointment) {
                if (((Appointment) appList.get(i)).getProfile().equals(profile) &&
                        ((Appointment) appList.get(i)).getDate().equals(date) &&
                        ((Appointment) appList.get(i)).getTimeslot().equals(timeslot)) {
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }

    /**
     * Checks if a timeslot is already taken for a specific provider.
     *
     * @param provider The provider whose timeslot availability is being checked.
     * @param timeslot The timeslot being checked.
     * @param appList The list of appointments to search through.
     * @return The index of the appointment if the timeslot is taken, or NOT_FOUND (-1) if not taken.
     */
    public int timeslotTaken(Provider provider, Timeslot timeslot, List<E> appList) {
        for (int i = 0; i < appList.size(); i++) {
            if (appList.get(i) instanceof Appointment) {
                if (((Appointment) appList.get(i)).getProvider().equals(provider) &&
                        ((Appointment) appList.get(i)).getTimeslot().equals(timeslot)) {
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }

    /**
     * Formats the timeslot into a readable string format.
     *
     * @param slotNumber The timeslot number to format.
     * @return A string representing the formatted timeslot, or an error message if the slot is invalid.
     */
    public String formatTimeslot(int slotNumber) {
        if (Timeslot.isValidSlotNum(slotNumber)) {
            Timeslot timeslot = Timeslot.getSlotTime(slotNumber);
            return timeslot.toString();  // Use Timeslot's toString method to format the time
        } else {
            return "Invalid time slot";  // Return error message if the slot number is invalid
        }
    }

    /**
     * Formats the date of an appointment into a "MM/DD/YYYY" format.
     *
     * @param appointment The appointment whose date is to be formatted.
     * @return A string representing the formatted date.
     */
    private String formatDate(Appointment appointment) {
        int day = appointment.getDate().getDay();
        int month = appointment.getDate().getMonth();
        int year = appointment.getDate().getYear();
        String formattedDate = String.format("%d/%d/%d", month, day, year);
        return formattedDate;
    }
}


