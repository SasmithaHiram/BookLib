package config;

import com.google.inject.AbstractModule;
import repository.custom.BookDao;
import repository.custom.impl.BookDaoImpl;
import service.custom.BookService;
import service.impl.BookServiceImpl;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BookService.class).to(BookServiceImpl.class);
        bind(BookDao.class).to(BookDaoImpl.class);

    }

}
