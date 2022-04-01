package persistence;

import domain.Patient;

import java.util.Arrays;

public class PatientRepository implements GenericRepository<Patient>{


    private Patient[] patients = new Patient[100];

    @Override
    public void add(Patient entity) {
        for (int i = 0; i< patients.length; i++) {
            if (patients[i] == null) {
                patients[i] = entity;
                return;
            }
        }
        Patient[] patientsTemp = Arrays.copyOf(patients, 2* patients.length, Patient[].class);
        patientsTemp[patients.length] = entity;
        patients = patientsTemp;
    }

    @Override
    public Patient get(int id) {
        return patients[id];
    }

    public void update_diagnosis(int id, String diagnosis) {
        for (Patient patient : patients)
        {
            if (patient != null && patient.getId() == id)
            {
                patient.setDiagnosis(diagnosis);
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i< patients.length; i++)
        {
            if (patients[i].getId() == id)
            {
                Patient[] tempPatients = new Patient[patients.length];
                System.arraycopy(patients,0, tempPatients,0,i);
                System.arraycopy(patients,i+1, tempPatients,i,getNumber()-1);
                patients = tempPatients;
                break;
            }
        }

    }

    @Override
    public int getSize() {
        return patients.length;
    }

    public boolean getID(int id)
    {
        for (int i = 0; i< patients.length && patients[i] != null; i++)
        {
            if (patients[i].getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    public int getNumber() {
        for (int i = 0; i< patients.length; i++)
        {
            if (patients[i] == null)
            {
                return i;
            }
        }
        return 0;
    }
}
