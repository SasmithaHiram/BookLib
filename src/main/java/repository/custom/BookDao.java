package repository.custom;

import dto.Book;
import entity.BookEntity;
import repository.CRUDRepository;

public interface BookDao extends CRUDRepository <BookEntity, String> {

}
