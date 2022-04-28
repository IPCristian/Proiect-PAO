package persistence;

import domain.Medical_Office;

import java.util.Arrays;

public class OfficeRepository implements GenericRepository<Medical_Office> {

    private Medical_Office[] offices = new Medical_Office[100];

    @Override
    public void add(Medical_Office entity) {
        for (int i=0;i<offices.length;i++)
        {
            if (offices[i] == null)
            {
                offices[i] = entity;
                return;
            }
        }
        Medical_Office[] officesTemp = Arrays.copyOf(offices, 2*offices.length,Medical_Office[].class);
        officesTemp[offices.length] = entity;
        offices = officesTemp;
    }

    @Override
    public Medical_Office get(int id) {
        return offices[id];
    }

    @Override
    public void delete(int id) {
        for (int i=0;i<offices.length;i++)
        {
            if (offices[i].getId_office() == id)
            {
                Medical_Office[] tempOffice = new Medical_Office[offices.length];
                System.arraycopy(offices,0,tempOffice,0,i);
                System.arraycopy(offices,i+1,tempOffice,i,getNumber()-1);
                offices = tempOffice;
                break;
            }
        }
    }

    @Override
    public int getSize() {
        return offices.length;
    }

    public int getNumber() {
        int number = 0;
        for (Medical_Office office : offices) {
            if (office != null) {
                number++;
            }
        }
        return number;
    }

    public boolean getID(int id)
    {
        for (int i = 0; i< offices.length && offices[i] != null; i++)
        {
            if (offices[i].getId_office() == id)
            {
                return true;
            }
        }
        return false;
    }
}
