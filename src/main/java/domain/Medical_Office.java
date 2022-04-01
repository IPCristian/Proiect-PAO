package domain;

public class Medical_Office {

    private int id_office;
    private int floor;
    private int door_number;

    public Medical_Office(int id_office, int floor, int door_number) {
        this.id_office = id_office;
        this.floor = floor;
        this.door_number = door_number;
    }

    public int getId_office() {
        return id_office;
    }

    public void setId_office(int id_office) {
        this.id_office = id_office;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getDoor_number() {
        return door_number;
    }

    public void setDoor_number(int door_number) {
        this.door_number = door_number;
    }
}
