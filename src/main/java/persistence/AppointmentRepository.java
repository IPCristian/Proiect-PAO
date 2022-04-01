package persistence;

import domain.Appointment;
import domain.Doctor;

import java.util.Arrays;

public class AppointmentRepository {

    private Appointment[] appointments = new Appointment[100];
    
    public void add(Appointment entity)
    {
        for (int i=0;i<appointments.length;i++)
        {
            if (appointments[i] == null)
            {
                appointments[i] = entity;
                return;
            }
        }
        Appointment[] appTemp = Arrays.copyOf(appointments, 2*appointments.length,Appointment[].class);
        appTemp[appointments.length] = entity;
        appointments = appTemp;
    }

    public int getSize() { return appointments.length;}

    public Appointment get(int id)
    {
        return appointments[id];
    }

}
