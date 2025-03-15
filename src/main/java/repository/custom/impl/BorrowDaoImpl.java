package repository.custom.impl;

import controller.BorrowDetailController;
import entity.BorrowEntity;
import repository.custom.BorrowDao;
import repository.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowDaoImpl implements BorrowDao {
    @Override
    public boolean save(BorrowEntity entity) {
        String SQL = "INSERT INTO borrow (borrow_id, member_id, borrow_date, dew_date, status) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, entity.getBorrowId());
            preparedStatement.setObject(2, entity.getMemberId());
            preparedStatement.setObject(3, entity.getBorrowDate());
            preparedStatement.setObject(4, entity.getDewDate());
            preparedStatement.setObject(5, entity.getStatus().name());
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public BorrowEntity search(String s) {
        return null;
    }

    @Override
    public boolean update(BorrowEntity entity) {
        String SQL = "UPDATE borrow SET member_id =?, borrow_date =?, dew_date =?, status =? WHERE borrow_id = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, entity.getMemberId());
            preparedStatement.setObject(2, entity.getBorrowDate());
            preparedStatement.setObject(3, entity.getDewDate());
            preparedStatement.setObject(4, entity.getStatus().toString());
            preparedStatement.setObject(5, entity.getBorrowId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<BorrowEntity> getAll() {
        return null;
    }

}
