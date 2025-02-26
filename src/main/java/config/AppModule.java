package config;

import com.google.inject.AbstractModule;
import repository.custom.BookDao;
import repository.custom.MemberDao;
import repository.custom.impl.BookDaoImpl;
import repository.custom.impl.MemberDaoImpl;
import service.custom.BookService;
import service.custom.MemberService;
import service.impl.BookServiceImpl;
import service.impl.MemberServiceImpl;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BookService.class).to(BookServiceImpl.class);
        bind(BookDao.class).to(BookDaoImpl.class);
        bind(MemberService.class).to(MemberServiceImpl.class);
        bind(MemberDao.class).to(MemberDaoImpl.class);
    }

}
