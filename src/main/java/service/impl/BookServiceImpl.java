package service.impl;

import com.google.inject.Inject;
import dto.Book;
import dto.BorrowDetails;
import entity.BookEntity;
import entity.BorrowDetailEntity;
import org.modelmapper.ModelMapper;
import repository.custom.BookDao;
import repository.custom.impl.BookDaoImpl;
import service.custom.BookService;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
//    @Inject
    BookDao dao = new BookDaoImpl();
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

    public boolean updateAvailability(List<BorrowDetails> borrowDetails) {
        List<BorrowDetailEntity> borrowDetailEntities = new ArrayList<>();

        for (BorrowDetails borrowDetail : borrowDetails) {
            BorrowDetailEntity entity = new ModelMapper().map(borrowDetail, BorrowDetailEntity.class);
            borrowDetailEntities.add(entity);
            boolean updateAvailability = updateAvailability(entity);

            if (!updateAvailability) {
                return false;
            }
        }
        return true;
    }

    private boolean updateAvailability(BorrowDetailEntity borrowDetail) {
        return dao.updateAvailability(borrowDetail);
    }

    public boolean updateReturnAvailability(List<BorrowDetails> borrowDetails) {
        List<BorrowDetailEntity> borrowDetailEntities = new ArrayList<>();

        for (BorrowDetails borrowDetail : borrowDetails) {
            BorrowDetailEntity entity = new ModelMapper().map(borrowDetail, BorrowDetailEntity.class);
            borrowDetailEntities.add(entity);
            boolean updateAvailability = updateReturnAvailability(entity);

            if (!updateAvailability) {
                return false;
            }
        }
        return true;
    }

    private boolean updateReturnAvailability(BorrowDetailEntity borrowDetail) {
        return dao.updateReturnBook(borrowDetail);
    }

}
