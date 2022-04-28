package persistence;

import domain.Medicine;

import java.util.Arrays;

public class MedicineRepository {

    private Medicine[] medicines = new Medicine[100];

    public void add(Medicine entity)
    {
        for (int i=0;i<medicines.length;i++)
        {
            if (medicines[i] == null)
            {
                medicines[i] = entity;
                return;
            }
        }
        Medicine[] medTemp = Arrays.copyOf(medicines, 2*medicines.length,Medicine[].class);
        medTemp[medicines.length] = entity;
        medicines = medTemp;
    }

    public int getSize() { return medicines.length;}

    public Medicine get(int id)
    {
        return medicines[id];
    }
}
