package domain;

public class Equipment {

    private int id_office;
    private int weight;

    public Equipment(int id_office, int weight) {
        this.id_office = id_office;
        this.weight = weight;
    }

    public int getId_office() {
        return id_office;
    }

    public void setId_office(int id_office) {
        this.id_office = id_office;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
