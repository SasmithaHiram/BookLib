package service.impl;

import com.google.inject.Inject;
import dto.Book;
import entity.BookEntity;
import org.modelmapper.ModelMapper;
import repository.custom.BookDao;
import service.custom.BookService;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    @Inject
    BookDao dao;
//  DaoFactory.getInstance().getDaoType(DaoType.BOOK);

    @Override
    public boolean addBook(Book book) {
        BookEntity map = new ModelMapper().map(book, BookEntity.class);
        boolean isSave = dao.save(map);

        return isSave;
    }

    @Override
    public Book searchBook(String id) {
        BookEntity search = dao.search(id);

        if (search != null) {
            return new ModelMapper().map(search, Book.class);
        }

        return null;
    }

    @Override
    public boolean updateBook(Book book) {
        BookEntity map = new ModelMapper().map(book, BookEntity.class);

        return dao.update(map);
    }

    @Override
    public boolean deleteBook(String id) {
        boolean isDelete = dao.delete(id);

        return isDelete;
    }

    @Override
    public List<Book> getAll() {
        List<BookEntity> all = dao.getAll();
        ArrayList<Book> books = new ArrayList<>();

        for (BookEntity entity : all) {
            Book map = new ModelMapper().map(entity, Book.class);
            books.add(map);
        }

        return books;
    }

}
