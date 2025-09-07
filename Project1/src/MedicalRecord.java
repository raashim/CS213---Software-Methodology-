/**
 * This class represents a collection of medical records for patients.
 * It maintains an array of Patient objects and keeps track of the number of patients added.
 */
public class MedicalRecord {
    private Patient[] patients;
    private int size;

    /**
     * Constructor to initialize the medical record with a specified capacity.
     *
     * @param length The maximum number of patients the record can hold.
     */
    public MedicalRecord(int length) {
        patients = new Patient[length];
        size = 0;
    }

    /**
     * Adds a new Patient to the medical record.
     * The patient is added to the first available position in the array.
     *
     * @param addPatient The Patient object to add to the medical record.
     */
    public void add(Patient addPatient) {
        for(int i = 0; i < patients.length; i++) {
            if (patients[i] == null) {
                patients[i] = addPatient;
                size++;
                break;
            }
        }
    }
}
