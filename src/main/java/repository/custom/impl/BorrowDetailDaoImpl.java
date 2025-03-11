package repository.custom.impl;

import dto.BorrowDetails;
import entity.BorrowDetailEntity;
import repository.custom.BorrowDetailDao;
import repository.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BorrowDetailDaoImpl implements BorrowDetailDao {
    @Override
    public boolean save(BorrowDetailEntity entity) {
        String SQL = "INSERT INTO borrow_detail VALUES (?, ?, ?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, entity.getBorrowId());
            preparedStatement.setObject(2, entity.getBookId());
            preparedStatement.setObject(3, entity.getBorrowDate());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public BorrowDetailEntity search(String s) {
        return null;
    }

    @Override
    public boolean update(BorrowDetailEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<BorrowDetailEntity> getAll() {
        return List.of();
    }
}
