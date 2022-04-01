package persistence;

public interface GenericRepository<T> {

    void add(T entity);

    T get(int id);

    void delete(int id);

    int getSize();

}
