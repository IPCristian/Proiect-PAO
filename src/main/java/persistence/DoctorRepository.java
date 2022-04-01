package persistence;

import domain.Doctor;

import java.util.Arrays;

public class DoctorRepository implements GenericRepository<Doctor> {

    private Doctor[] doctors = new Doctor[100];

    @Override
    public void add(Doctor entity) {
        for (int i=0;i<doctors.length;i++)
        {
            if (doctors[i] == null)
            {
                doctors[i] = entity;
                return;
            }
        }
        Doctor[] doctorsTemp = Arrays.copyOf(doctors, 2*doctors.length,Doctor[].class);
        doctorsTemp[doctors.length] = entity;
        doctors = doctorsTemp;
    }

    @Override
    public Doctor get(int id) {
        return doctors[id];
    }

    public void update_spec(int id, String Specialization) {

        for (Doctor doctor : doctors)
        {
            if (doctor.getId() == id)
            {
                doctor.setSpecialization(Specialization);
                break;
            }
        }

    }

    @Override
    public void delete(int id) {
        for (int i=0;i<doctors.length;i++)
        {
            if (doctors[i].getId() == id)
            {
                Doctor[] tempDoctors = new Doctor[doctors.length];
                System.arraycopy(doctors,0,tempDoctors,0,i);
                System.arraycopy(doctors,i+1,tempDoctors,i,getNumber()-1);
                doctors = tempDoctors;
                break;
            }
        }
    }

    @Override
    public int getSize() {
        return doctors.length;
    }

    public int getNumber() {
        int number = 0;
        for (Doctor doctor : doctors) {
            if (doctor != null) {
                number++;
            }
        }
        return number;
    }

    public boolean getID(int id)
    {
        for (int i=0;i<doctors.length && doctors[i] != null;i++)
        {
            if (doctors[i].getId() == id)
            {
                return true;
            }
        }
        return false;
    }
}
