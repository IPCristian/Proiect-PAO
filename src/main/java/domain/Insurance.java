package domain;

public class Insurance {

    private int id_person;
    private int year_expire;

    public Insurance(int id_person, int year_expire) {
        this.id_person = id_person;
        this.year_expire = year_expire;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public int getYear_expire() {
        return year_expire;
    }

    public void setYear_expire(int year_expire) {
        this.year_expire = year_expire;
    }
}
