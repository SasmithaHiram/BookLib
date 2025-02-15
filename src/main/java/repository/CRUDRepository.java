package repository;

import entity.BookEntity;

import java.util.List;

public interface CRUDRepository <T, Id> extends SuperDao {
    boolean save(T entity);
    BookEntity search(Id id);
    boolean update(T entity);
    boolean delete(Id id);
    List<T> getAll();

}
