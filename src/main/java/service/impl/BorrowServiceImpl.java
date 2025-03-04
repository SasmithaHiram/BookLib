package service.impl;

import com.google.inject.Inject;
import dto.Borrow;
import entity.BorrowEntity;
import org.modelmapper.ModelMapper;
import repository.custom.BorrowDao;
import service.custom.BorrowService;

public class BorrowServiceImpl implements BorrowService {
    @Inject
    BorrowDao borrowDao;

    @Override
    public boolean placeBorrowOrder(Borrow borrow) {
        BorrowEntity map = new ModelMapper().map(borrow, BorrowEntity.class);
        return borrowDao.save(map);
    }
    
}
