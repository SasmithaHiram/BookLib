package repository.custom.impl;

import entity.FineEntity;
import repository.custom.FineDao;
import repository.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FineDaoImpl implements FineDao {
    @Override
    public boolean save(FineEntity entity) {
        String SQL = "INSERT INTO fines (borrow_id, borrow_date, due_date, return_date, fine_amount, pay_amount) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, entity.getBorrowId());
            preparedStatement.setObject(2, entity.getBorrowDate());
            preparedStatement.setObject(3, entity.getDewDate());
            preparedStatement.setObject(4, entity.getReturnDate());
            preparedStatement.setObject(5, entity.getFineAmount());
            preparedStatement.setObject(6, entity.getPayAmount());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public FineEntity search(Integer integer) {
        return null;
    }

    @Override
    public boolean update(FineEntity entity) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public List<FineEntity> getAll() {
        return List.of();
    }
}
