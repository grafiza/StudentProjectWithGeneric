package StudentManagementProject2;
public interface GenericService<T> {
    void createTable();
    void save();
    void getAll();
    void delete(int id);
    void display(int id);
    void update(int id);
    T getById(int id);
}
