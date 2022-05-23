package service;

import domain.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDService {

    private static CRUDService instance = null;
    private final ConnectionManager connectionManager;

    private CRUDService() throws SQLException, ClassNotFoundException {
        connectionManager = new ConnectionManager("jdbc:oracle:thin:@localhost:1521:ORCLCDB", "c##some_username", "parola");
    }

    public static CRUDService getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
        {
            instance = new CRUDService();
        }
        return instance;
    }

    public void Create(Doctor d) throws SQLException {
        PreparedStatement st = connectionManager.prepareStatement("insert into doctor values (" + d.getId() + ",'" +
                d.getLastName() + "','" + d.getFirstName() + "','" + d.getEmail() + "'," + d.getAge() + "," +
                d.getYearsOfExperience() + ",'" + d.getSpecialization() + "')");

        st.executeQuery();
    }
    public void Create(Patient p, Insurance i) throws SQLException {

        PreparedStatement st = connectionManager.prepareStatement("insert into patient values (" + p.getId() + ",'" +
                p.getLastName() + "','" + p.getFirstName() + "','" + p.getEmail() + "'," + p.getAge() + "," +
                p.getDoctor_id() + ",'" + p.getDiagnosis() + "'," +  i.getYear_expire() + ")");

        st.executeQuery();
    }
    public void Create(Medical_Office o) throws SQLException {
        PreparedStatement st = connectionManager.prepareStatement("insert into office values (" + o.getId_office() +
                "," + o.getFloor() + "," + o.getDoor_number() + ")");

        st.executeQuery();
    }
    public void Create(Appointment a) throws SQLException {
        PreparedStatement st = connectionManager.prepareStatement("insert into appointment values (" + a.getDoctor_id()
                + "," + a.getPatient_id() + "," + a.getOffice_id() + "," + a.getHour() + "," + a.getMinute() + ")");

        st.executeQuery();
    }

    public void Delete(int id,String table) throws SQLException {
        PreparedStatement st = connectionManager.prepareStatement("delete from " + table + " where id = " + id);
        st.executeQuery();
    }

    public void Delete(int doctor_id,int patient_id,int office_id) throws SQLException {
        PreparedStatement st = connectionManager.prepareStatement("delete from appointment where doctor_id = " +
                doctor_id + " and patient_id = " + patient_id + " and office_id = " + office_id);
        st.executeQuery();
    }

    public ResultSet Get(String table) throws SQLException {
        PreparedStatement st = connectionManager.prepareStatement("select * from " + table);
        return st.executeQuery();
    }

    public void Update(String table, int id, String change) throws SQLException {
        switch (table) {
            case "doctor" -> {
                PreparedStatement st = connectionManager.prepareStatement("update doctor set specialization = '"
                        + change + "' where id = " + id);
                st.executeQuery();

            }
            case "patient" -> {
                PreparedStatement st = connectionManager.prepareStatement("update patient set diagnosis = '" + change +
                        "' where id = " + id);
                st.executeQuery();
            }
        }
    }

    public void Update(String table, int id, int door_number) throws SQLException {
        if (table.equals("office"))
        {
            PreparedStatement st = connectionManager.prepareStatement("update office set door_number = "
                    + door_number + " where id = " + id);
            st.executeQuery();
        }
    }

    // Reschedule an appointment
    public void Update(String table, int doctor_id, int patient_id, int office_id, int hour, int minute) throws SQLException {
        if (table.equals("appointment"))
        {
            PreparedStatement st = connectionManager.prepareStatement("update appointment set hour = " + hour
                    + " and minute = " + minute + " where doctor_id = " + doctor_id + " and patient_id = " + patient_id
                    + " and office_id = " + office_id);

            st.executeQuery();
        }
    }

    public void Commit() throws SQLException {
        PreparedStatement st = connectionManager.prepareStatement("commit");
        st.executeQuery();
    }

    public void CloseConnection() throws SQLException {
        connectionManager.close();
    }
}
