package service.impl;

import com.google.inject.Inject;
import dto.BorrowDetails;
import entity.BorrowDetailEntity;
import org.modelmapper.ModelMapper;
import repository.custom.BorrowDetailDao;
import repository.custom.impl.BorrowDetailDaoImpl;
import service.custom.BorrowDetailService;

public class BorrowDetailServiceImpl implements BorrowDetailService {
    //@Inject
    BorrowDetailDao borrowDetailDao = new BorrowDetailDaoImpl();

    @Override
    public boolean addBorrowDetail(BorrowDetails borrowDetails) {
        BorrowDetailEntity map = new ModelMapper().map(borrowDetails, BorrowDetailEntity.class);
        return borrowDetailDao.save(map);
    }

    @Override
    public boolean updateBorrowDetail(BorrowDetails borrowDetails) {
        BorrowDetailEntity map = new ModelMapper().map(borrowDetails, BorrowDetailEntity.class);
        return borrowDetailDao.update(map);
    }

}
