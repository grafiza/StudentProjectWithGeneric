package StudentManagementProject2;

import java.util.List;

public interface Repository<T> {

    void save(T object);
    T findById(int id);
    List<String> findAll();
    void update(T object);
    void delete(int id);
}
