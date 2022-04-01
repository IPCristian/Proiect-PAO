package persistence;

import domain.Doctor;
import domain.Pacient;

import java.util.Arrays;
import java.util.HashSet;

public class PacientRepository implements GenericRepository<Pacient>{

    //private HashSet<Pacient> Pacients = new HashSet<>();
    private Pacient[] pacients = new Pacient[100];

    @Override
    public void add(Pacient entity) {
        for (int i=0;i<pacients.length;i++) {
            if (pacients[i] == null) {
                pacients[i] = entity;
                return;
            }
        }
        Pacient[] pacientsTemp = Arrays.<Pacient,Pacient>copyOf(pacients, 2*pacients.length,Pacient[].class);
        pacientsTemp[pacients.length] = entity;
        pacients = pacientsTemp;
    }

    @Override
    public Pacient get(int id) {
        return pacients[id];
    }

    @Override
    public void update(Pacient entity, int id) {
        for (Pacient pacient : pacients) {
            if (pacient.getId() == id) {
                pacient.setLastName(entity.getLastName());
                pacient.setFirstName(entity.getFirstName());
                pacient.setEmail(entity.getEmail());
                pacient.setAge(entity.getAge());
                pacient.setDoctor_id(entity.getDoctor_id());
                pacient.setDiagnosis(entity.getDiagnosis());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i=0;i<pacients.length;i++)
        {
            if (pacients[i].getId() == id)
            {
                Pacient[] tempPacients = new Pacient[pacients.length];
                System.arraycopy(pacients,0,tempPacients,0,i);
                System.arraycopy(pacients,i+1,tempPacients,i,getNumber()-1);
                pacients = tempPacients;
                break;
            }
        }

    }

    @Override
    public int getSize() {
        return pacients.length;
    }

    public boolean getID(int id)
    {
        for (int i=0;i<pacients.length && pacients[i] != null;i++)
        {
            if (pacients[i].getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    public int getNumber() {
        for (int i=0;i<pacients.length;i++)
        {
            if (pacients[i] == null)
            {
                return i;
            }
        }
        return 0;
    }
}
