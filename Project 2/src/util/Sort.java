package util;

import softmeth.project2.Appointment;
import softmeth.project2.Patient;
import softmeth.project2.Provider;
import util.ListMethods;

/**
 * Provides various sorting functionalities for lists of providers, appointments, and patients.
 * This class contains static methods to sort objects by different criteria.
 *
 * @author Raashi Maheshwari
 * @author Tanvi Yamarty
 */
public class Sort {

    /**
     * Sorts a list of providers by their last names using bubble sort.
     *
     * @param provider List of providers to be sorted.
     * @return Sorted list of providers.
     */
    public static List<Provider> sortProviders(List<Provider> provider) {
        for (int i = 0; i < provider.size() - 1; i++) {
            for (int j = 0; j < provider.size() - i - 1; j++) {
                String lastName1 = provider.get(j).getProfile().getLname();
                String lastName2 = provider.get(j + 1).getProfile().getLname();
                if (lastName1.compareTo(lastName2) > 0) {
                    Provider temp = provider.get(j);
                    provider.set(j, provider.get(j + 1));
                    provider.set(j + 1, temp);
                }
            }
        }
        return provider;
    }

    /**
     * Sorts a list of appointments by date and time using bubble sort.
     *
     * @param appointments List of appointments to be sorted.
     */
    public static void sortByAppointment(List<Appointment> appointments) {
        int size = appointments.size(); // Get the size of the list
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments.get(j).compareByAppointment(appointments.get(j + 1)) > 0) {
                    Appointment temp = appointments.get(j);
                    appointments.set(j, appointments.get(j + 1));
                    appointments.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Sorts a list of appointments by patient name using bubble sort.
     *
     * @param appointments List of appointments to be sorted by patient.
     */
    public static void sortByPatient(List<Appointment> appointments) {
        for (int i = 0; i < appointments.getSize() - 1; i++) {
            for (int j = 0; j < appointments.size() - i - 1; j++) {
                if (appointments.get(j).compareTo(appointments.get(j + 1)) > 0) {
                    Appointment temp = appointments.get(j);
                    appointments.set(j, appointments.get(j + 1));
                    appointments.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Sorts a list of appointments by provider location using bubble sort.
     *
     * @param appointments List of appointments to be sorted by location.
     */
    public static void sortByLocation(List<Appointment> appointments) {
        for (int i = 0; i < appointments.getSize() - 1; i++) {
            for (int j = 0; j < appointments.getSize() - i - 1; j++) {
                if (appointments.get(j).compareByLocation(appointments.get(j + 1)) > 0) {
                    Appointment temp = appointments.get(j);
                    appointments.set(j, appointments.get(j + 1));
                    appointments.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Sorts a list of patients by their profile (name) using bubble sort.
     *
     * @param patients List of patients to be sorted.
     */
    public static void sortPatientsByName(List<Patient> patients) {
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = 0; j < patients.size() - i - 1; j++) {
                Patient p1 = patients.get(j);
                Patient p2 = patients.get(j + 1);
                if (p1.getProfile().compareTo(p2.getProfile()) > 0) {
                    patients.set(j, p2);
                    patients.set(j + 1, p1);
                }
            }
        }
    }

    /**
     * Sorts a list of imaging appointments by provider's county, date, and timeslot.
     *
     * @param imagingAppointments List of imaging appointments to be sorted.
     */
    public static void sortImagingAppointments(List<Appointment> imagingAppointments) {
        for (int i = 0; i < imagingAppointments.size() - 1; i++) {
            for (int j = 0; j < imagingAppointments.size() - i - 1; j++) {
                Appointment current = imagingAppointments.get(j);
                Appointment next = imagingAppointments.get(j + 1);

                int countyComparison = current.getProvider().getLocation().getCounty()
                        .compareTo(next.getProvider().getLocation().getCounty());

                if (countyComparison > 0) {
                    imagingAppointments.set(j, next);
                    imagingAppointments.set(j + 1, current);
                } else if (countyComparison == 0) {
                    int dateComparison = current.getDate().compareTo(next.getDate());
                    if (dateComparison > 0) {
                        imagingAppointments.set(j, next);
                        imagingAppointments.set(j + 1, current);
                    } else if (dateComparison == 0) {
                        int timeComparison = current.getTimeslot().compareTo(next.getTimeslot());

                        if (timeComparison > 0) {
                            imagingAppointments.set(j, next);
                            imagingAppointments.set(j + 1, current);
                        }
                    }
                }
            }
        }
    }

    /**
     * Sorts an array of imaging appointments by provider's county, date, and timeslot.
     *
     * @param appointments Array of imaging appointments to be sorted.
     * @param size Size of the array.
     */
    public static void sortImagingAppointments(Appointment[] appointments, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                int countyComparison = appointments[j].getProvider().getLocation().getCounty()
                        .compareTo(appointments[j + 1].getProvider().getLocation().getCounty());

                if (countyComparison > 0) {
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;
                } else if (countyComparison == 0) {
                    int dateComparison = appointments[j].getDate().compareTo(appointments[j + 1].getDate());

                    if (dateComparison > 0) {
                        Appointment temp = appointments[j];
                        appointments[j] = appointments[j + 1];
                        appointments[j + 1] = temp;
                    } else if (dateComparison == 0) {
                        int timeComparison = appointments[j].getTimeslot().compareTo(appointments[j + 1].getTimeslot());
                        if (timeComparison > 0) {
                            Appointment temp = appointments[j];
                            appointments[j] = appointments[j + 1];
                            appointments[j + 1] = temp;
                        }
                    }
                }
            }
        }
    }

    /**
     * Sorts a list of providers by their profile (name) using bubble sort.
     *
     * @param providers List of providers to be sorted.
     */
    public static void sortProvidersByProfile(List<Provider> providers) {
        for (int i = 0; i < providers.size() - 1; i++) {
            for (int j = 0; j < providers.size() - i - 1; j++) {
                Provider p1 = providers.get(j);
                Provider p2 = providers.get(j + 1);
                if (p1.getProfile().compareTo(p2.getProfile()) > 0) {
                    providers.set(j, p2);
                    providers.set(j + 1, p1);
                }
            }
        }
    }
}