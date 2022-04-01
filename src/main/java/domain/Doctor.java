package domain;

public class Doctor extends Person {

    private int yearsOfExperience;
    private String specialization;

    public Doctor(int id, String lastName, String firstName, String email, int age, int yearsOfExperience, String specialization) {
        super(id, lastName, firstName, email, age);
        this.yearsOfExperience = yearsOfExperience;
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
