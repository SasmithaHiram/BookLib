package config;

import com.google.inject.AbstractModule;
import repository.custom.BookDao;
import repository.custom.BorrowDao;
import repository.custom.MemberDao;
import repository.custom.UserDao;
import repository.custom.impl.BookDaoImpl;
import repository.custom.impl.BorrowDaoImpl;
import repository.custom.impl.MemberDaoImpl;
import repository.custom.impl.UserDaoImp;
import service.custom.BookService;
import service.custom.BorrowService;
import service.custom.MemberService;
import service.custom.UserService;
import service.impl.BookServiceImpl;
import service.impl.BorrowServiceImpl;
import service.impl.MemberServiceImpl;
import service.impl.UserServiceImpl;

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
    }

}
