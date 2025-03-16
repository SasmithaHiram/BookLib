package service.impl;

import com.google.inject.Inject;
import dto.Fine;
import entity.FineEntity;
import org.modelmapper.ModelMapper;
import repository.custom.FineDao;
import service.custom.FineService;

public class FineServiceImpl implements FineService {
    @Inject
    FineDao fineDao;

    @Override
    public boolean addFine(Fine fine) {
        FineEntity map = new ModelMapper().map(fine, FineEntity.class);
        return fineDao.save(map);
    }

}
