package service.custom;

import dto.Book;
import service.SuperService;

import java.util.List;

public interface BookService extends SuperService {
    boolean addBook(Book book);
    boolean searchBook(String id);
    boolean updateBook(Book book, String id);
    boolean deleteBook(String id);
    List<Book> getAll();
}
