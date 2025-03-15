package repository.custom;

import dto.Book;
import entity.BookEntity;
import entity.BorrowDetailEntity;
import repository.CRUDRepository;

public interface BookDao extends CRUDRepository <BookEntity, String> {
    boolean updateAvailability(BorrowDetailEntity borrowDetailEntity);
    boolean updateReturnBook(BorrowDetailEntity borrowDetailEntity);

}
