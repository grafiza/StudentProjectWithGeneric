package StudentManagementProject2;

public interface Repository<T> {

    void save(T object);
    T findById(int id);
    void findAll();
    void update(T object);
    void delete(int id);
}
