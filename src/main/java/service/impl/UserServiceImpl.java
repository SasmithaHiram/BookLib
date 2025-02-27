package service.impl;

import com.google.inject.Inject;
import dto.User;
import entity.UserEntity;
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
        UserEntity map = new ModelMapper().map(user, UserEntity.class);
        return dao.save(map);
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
