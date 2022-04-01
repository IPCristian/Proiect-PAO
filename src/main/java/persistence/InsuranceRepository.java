package persistence;

import domain.Insurance;

import java.util.HashSet;

public class InsuranceRepository implements GenericRepository<Insurance> {

    HashSet<Insurance> set= new HashSet<>();

    @Override
    public void add(Insurance entity) {
        set.add(entity);
    }

    @Override
    public Insurance get(int id) {
        for (Insurance insurance : set)
        {
            if (insurance.getId_person() == id)
            {
                return insurance;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        for (Insurance insurance : set)
        {
            if (insurance.getId_person() == id)
            {
                set.remove(insurance);
                break;
            }
        }
    }

    @Override
    public int getSize() {
        return set.size();
    }
}
