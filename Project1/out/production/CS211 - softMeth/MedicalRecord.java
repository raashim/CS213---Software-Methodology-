public class MedicalRecord {
    private Patient[] patients;
    private int size; //number of patient objects in the array

    public MedicalRecord(int length) {
        patients = new Patient[length];
        size = 0;
    }
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
