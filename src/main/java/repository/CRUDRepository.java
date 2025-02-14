package repository;

import java.util.List;

public interface CRUDRepository <T, Id> extends SuperDao {
    boolean save(T entity);
    T search(Id id);
    boolean update(T entity, Id id);
    boolean delete(Id id);
    List<T> getAll();

}
