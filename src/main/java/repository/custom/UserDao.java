package repository.custom;

import entity.UserEntity;
import repository.CRUDRepository;

import java.util.List;

public interface UserDao extends CRUDRepository<UserEntity, String> {
}
