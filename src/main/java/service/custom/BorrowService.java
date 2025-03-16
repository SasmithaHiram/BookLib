package service.custom;

import dto.Borrow;

import java.util.List;

public interface BorrowService {
    boolean placeBorrowOrder(Borrow borrow);
    List<Borrow> getAllBorrorw();
    boolean UpdateBorrowOrder(Borrow borrow);
}
