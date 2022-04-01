package persistence;

import domain.Doctor;
import domain.Pacient;

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
        Doctor[] doctorsTemp = Arrays.<Doctor,Doctor>copyOf(doctors, 2*doctors.length,Doctor[].class);
        doctorsTemp[doctors.length] = entity;
        doctors = doctorsTemp;
    }

    @Override
    public Doctor get(int id) {
        return doctors[id];
    }

    @Override
    public void update(Doctor entity, int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                doctor.setLastName(entity.getLastName());
                doctor.setFirstName(entity.getFirstName());
                doctor.setEmail(entity.getEmail());
                doctor.setAge(entity.getAge());
                doctor.setYearsOfExperience(entity.getYearsOfExperience());
                doctor.setSpecialization(entity.getSpecialization());
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
        for (int i=0;i<doctors.length;i++)
        {
            if (doctors[i] == null)
            {
                return i;
            }
        }
        return 0;
    }
}
