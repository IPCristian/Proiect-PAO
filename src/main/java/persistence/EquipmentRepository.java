package persistence;

import domain.Equipment;

import java.util.Arrays;

public class EquipmentRepository {

    private Equipment[] equipments = new Equipment[100];

    public void add(Equipment entity)
    {
        for (int i=0;i<equipments.length;i++)
        {
            if (equipments[i] == null)
            {
                equipments[i] = entity;
                return;
            }
        }
        Equipment[] appTemp = Arrays.copyOf(equipments, 2*equipments.length,Equipment[].class);
        appTemp[equipments.length] = entity;
        equipments = appTemp;
    }

    public int getSize() { return equipments.length;}

    public Equipment get(int id)
    {
        return equipments[id];
    }
}
