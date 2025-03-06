package service.impl;

import com.google.inject.Inject;
import dto.User;
import entity.UserEntity;
import org.jasypt.util.text.BasicTextEncryptor;
import org.modelmapper.ModelMapper;
import repository.custom.UserDao;
import service.custom.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Inject
    UserDao dao;

    @Override
    public boolean saveUser(User user) {
        String key = "#5541Asd";
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        user.setPassword(basicTextEncryptor.encrypt(user.getPassword()));
        UserEntity map = new ModelMapper().map(user, UserEntity.class);
        return dao.save(map);
    }

    @Override
    public User searchByEmail(String txtEmail) {
        String key = "#5541Asd";
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        UserEntity search = dao.search(txtEmail);
        search.setPassword(basicTextEncryptor.decrypt(search.getPassword()));

        if (search!=null) {
            return new ModelMapper().map(search, User.class);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        List<UserEntity> allUsers = dao.getAll();

        allUsers.forEach(userEntity -> {
            User map = new ModelMapper().map(allUsers, User.class);
            users.add(map);
        });
        return users;
    }

}
