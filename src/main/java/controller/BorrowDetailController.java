package controller;

import com.google.inject.Inject;
import dto.BorrowDetails;
import service.custom.BorrowDetailService;
import service.impl.BorrowDetailServiceImpl;

import java.util.List;

public class BorrowDetailController {
    @Inject
    BorrowDetailService borrowDetailService = new BorrowDetailServiceImpl();

    public boolean addBorrowDetail(List<BorrowDetails> borrowDetails) {
        for (BorrowDetails borrowDetail : borrowDetails) {
            boolean isAddBorrowDetail = addBorrowDetail(borrowDetail);

            if (!isAddBorrowDetail) {
                return false;
            }
        }
        return true;
    }

    public boolean addBorrowDetail(BorrowDetails borrowDetail) {
        return borrowDetailService.addBorrowDetail(borrowDetail);
    }

    public boolean updateBorrowDetail(List<BorrowDetails> borrowDetails) {
        for (BorrowDetails borrowDetail : borrowDetails) {
            boolean isUpdateBorrowDetail = updateBorrowDetail(borrowDetail);

            if (!isUpdateBorrowDetail) {
                return false;
            }
        }
        return true;
    }

    public boolean updateBorrowDetail(BorrowDetails borrowDetail) {
        return borrowDetailService.updateBorrowDetail(borrowDetail);
    }

}
