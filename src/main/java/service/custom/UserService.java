package service.custom;

import dto.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);
    List<User> getAllUsers();
}
