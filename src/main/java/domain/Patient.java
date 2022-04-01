package domain;

public class Patient extends Person {

    private int doctor_id;
    private String diagnosis;
    private Insurance insurance;

    public Patient(int id, String lastName, String firstName, String email, int age, int doctor_id, String diagnosis, Insurance insurance) {
        super(id, lastName, firstName, email, age);
        this.doctor_id = doctor_id;
        this.diagnosis = diagnosis;
        this.insurance = insurance;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
