package service.impl;

import dto.Borrow;
import entity.BorrowEntity;
import org.modelmapper.ModelMapper;
import repository.custom.BorrowDao;
import repository.custom.impl.BorrowDaoImpl;
import service.custom.BorrowService;

import java.util.ArrayList;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    //@Inject
    BorrowDao borrowDao = new BorrowDaoImpl();

    @Override
    public boolean placeBorrowOrder(Borrow borrow) {
        BorrowEntity map = new ModelMapper().map(borrow, BorrowEntity.class);
        return borrowDao.save(map);


    }

    @Override
    public List<Borrow> getAllBorrorw() {
        List<BorrowEntity> all = borrowDao.getAll();

        List<Borrow> borrows = new ArrayList<>();

        all.forEach(order -> {
            Borrow map = new ModelMapper().map(all, Borrow.class);
            borrows.add(map);
        });
        return borrows;
    }

    @Override
    public boolean UpdateBorrowOrder(Borrow borrow) {
        BorrowEntity map = new ModelMapper().map(borrow, BorrowEntity.class);
        boolean isUpdated = borrowDao.update(map);

        if (isUpdated) {
            new BookServiceImpl().updateReturnAvailability(borrow.getBorrowedBooks());
        }
        return false;
    }

}
