package config;

import com.google.inject.AbstractModule;
import repository.custom.*;
import repository.custom.impl.*;
import service.custom.*;
import service.impl.*;

import static org.modelmapper.internal.bytebuddy.asm.Advice.to;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BookService.class).to(BookServiceImpl.class);
        bind(BookDao.class).to(BookDaoImpl.class);
        bind(MemberService.class).to(MemberServiceImpl.class);
        bind(MemberDao.class).to(MemberDaoImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(UserDao.class).to(UserDaoImp.class);
        bind(BorrowService.class).to(BorrowServiceImpl.class);
        bind(BorrowDao.class).to(BorrowDaoImpl.class);
        bind(BorrowDetailService.class).to(BorrowDetailServiceImpl.class);
        bind(BorrowDetailDao.class).to(BorrowDetailDaoImpl.class);
        bind(ReturnService.class).to(ReturnServiceImpl.class);
        bind(ReturnDao.class).to(ReturnDaoImpl.class);
        bind(FineService.class).to(FineServiceImpl.class);
        bind(FineDao.class).to(FineDaoImpl.class);
    }

}
