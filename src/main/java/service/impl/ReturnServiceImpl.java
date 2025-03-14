package service.impl;

import com.google.inject.Inject;
import dto.Return;
import entity.ReturnEntity;
import org.modelmapper.ModelMapper;
import repository.custom.ReturnDao;
import service.custom.ReturnService;

public class ReturnServiceImpl implements ReturnService {
    @Inject
    ReturnDao returnDao;
    @Override
    public Return search(String id) {
        ReturnEntity search = returnDao.search(id);

        if (search!=null) {
            return new ModelMapper().map(search, Return.class);
        }
        return null;
    }

}
