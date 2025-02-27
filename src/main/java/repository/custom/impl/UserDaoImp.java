package repository.custom.impl;

import entity.UserEntity;
import repository.custom.UserDao;
import repository.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {

    @Override
    public boolean save(UserEntity entity) {
        String SQL = "INSERT INTO USERS (username, email, password) VALUES(?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPassword());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserEntity search(String s) {
        return null;
    }

    @Override
    public boolean update(UserEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<UserEntity> getAll() {
        String SQL = "SELECT *form users";

        List<UserEntity> userEntities = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);

            while (resultSet.next()) {
                UserEntity userEntity = new UserEntity(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
                userEntities.add(userEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userEntities;
    }
}
