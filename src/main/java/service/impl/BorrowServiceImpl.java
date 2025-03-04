package service.impl;

import com.google.inject.Inject;
import dto.Borrow;
import entity.BorrowEntity;
import org.modelmapper.ModelMapper;
import repository.custom.BorrowDao;
import service.custom.BorrowService;

import java.util.ArrayList;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    @Inject
    BorrowDao borrowDao;

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

}
