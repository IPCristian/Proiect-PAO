package domain;

public class Pacient extends Person {

    private int doctor_id;
    private String diagnosis;

    public Pacient(int id, String lastName, String firstName, String email, int age, int doctor_id, String diagnosis) {
        super(id, lastName, firstName, email, age);
        this.doctor_id = doctor_id;
        this.diagnosis = diagnosis;
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
