package domain;

public class Appointment {

    private int doctor_id;
    private int patient_id;
    private int office_id;
    private int hour;
    private int minute;

    public Appointment(int doctor_id, int patient_id, int office_id, int hour, int minute) {
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.office_id = office_id;
        this.hour = hour;
        this.minute = minute;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getOffice_id() {
        return office_id;
    }

    public void setOffice_id(int office_id) {
        this.office_id = office_id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
