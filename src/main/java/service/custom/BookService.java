package service.custom;

import dto.Book;
import service.SuperService;

import java.util.List;

public interface BookService extends SuperService {
    boolean addBook(Book book);
    Book searchBook(String id);
    boolean updateBook(Book book);
    boolean deleteBook(String id);
    List<Book> getAll();

}
