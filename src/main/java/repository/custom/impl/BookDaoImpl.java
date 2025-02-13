package repository.custom.impl;

import entity.BookEntity;
import repository.custom.BookDao;

import java.util.List;

public class BookDaoImpl implements BookDao{

    @Override
    public boolean save(BookEntity entity) {
        System.out.println(entity);
        return false;
    }

    @Override
    public BookEntity search(String s) {
        return null;
    }

    @Override
    public boolean update(BookEntity entity, String s) {
        return false;
    }

    @Override
    public boolean update(String s) {
        return false;
    }

    @Override
    public List<BookEntity> getAll() {
        return List.of();
    }
}
