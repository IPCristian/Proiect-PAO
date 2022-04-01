package persistence;

public interface GenericRepository<T> {

    public void add(T entity);

    public T get(int id);

    public void update(T entity, int id);

    public void delete(int id);

    public int getSize();

}
