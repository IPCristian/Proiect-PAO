package persistence;

import domain.Appointment;

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

    public void delete(int doctor_id,int patient_id,int office_id) {
        for (int i=0;i<appointments.length;i++)
        {
            if (appointments[i].getDoctor_id() == doctor_id && appointments[i].getPatient_id() == patient_id && appointments[i].getOffice_id() == office_id)
            {
                Appointment[] tempApps = new Appointment[appointments.length];
                System.arraycopy(appointments,0,tempApps,0,i);
                System.arraycopy(appointments,i+1,tempApps,i,getNumber()-1);
                appointments = tempApps;
                break;
            }
        }
    }

    public int getNumber() {
        int number = 0;
        for (Appointment appointment : appointments) {
            if (appointment != null) {
                number++;
            }
        }
        return number;
    }

    public int getSize() { return appointments.length;}

    public Appointment get(int id)
    {
        return appointments[id];
    }

    public boolean getID(int doctor_id,int patient_id, int office_id)
    {
        for (int i=0;i<appointments.length && appointments[i] != null;i++)
        {
            if (appointments[i].getDoctor_id() == doctor_id && appointments[i].getPatient_id() == patient_id && appointments[i].getOffice_id() == office_id)
            {
                return true;
            }
        }
        return false;
    }

}
