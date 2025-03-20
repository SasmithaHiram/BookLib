package repository.custom.impl;

import entity.ReturnEntity;
import repository.custom.ReturnDao;
import repository.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReturnDaoImpl implements ReturnDao {
    @Override
    public boolean save(ReturnEntity entity) {
        return false;
    }

    @Override
    public ReturnEntity search(String s) {
        String SQL = "SELECT b.borrow_id, " +
                "b.member_id, " +
                "b.borrow_date, " +
                "b.dew_date, " +
                "b.status AS borrow_status, " +
                "bd.borrow_id,"+
                "bd.book_id, " +
                "bd.return_date, " +
                "bd.status AS book_status " +
                "FROM borrow b " +
                "LEFT JOIN borrow_detail bd ON b.borrow_id = bd.borrow_id " +
                "WHERE b.borrow_id = ?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                return new ReturnEntity(
                        resultSet.getString("member_id"),
                        resultSet.getString("book_id"),
                        resultSet.getDate("borrow_date"),
                        resultSet.getDate("dew_date"),
                        resultSet.getDate("return_date")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    @Override
    public boolean update(ReturnEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<ReturnEntity> getAll() {
        return List.of();
    }
}
