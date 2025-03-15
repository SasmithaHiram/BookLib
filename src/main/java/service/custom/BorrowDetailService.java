package service.custom;

import dto.BorrowDetails;
import repository.CRUDRepository;

public interface BorrowDetailService {
    boolean addBorrowDetail(BorrowDetails borrowDetails);
    boolean updateBorrowDetail(BorrowDetails borrowDetails);
}
