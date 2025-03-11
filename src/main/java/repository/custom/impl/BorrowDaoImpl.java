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
        String SQL = "INSERT INTO borrow  (borrow_id, member_id, book_id, borrow_date, dew_date, status)  VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, entity.getBorrowId());
            preparedStatement.setObject(2, entity.getMemberId());
            preparedStatement.setObject(3, entity.getBookId());
            preparedStatement.setObject(4, entity.getBorrowDate());
            preparedStatement.setObject(5, entity.getDewDate());
            preparedStatement.setObject(6, entity.getStatus().name());
            boolean isAddBorrow = preparedStatement.executeUpdate() > 0;

            if (isAddBorrow) {
                boolean isAddBorrowDetail = new BorrowDetailController().addBorrowDetail(entity.getBorrowedBooks());

                if (isAddBorrowDetail) {
                    return true;
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public BorrowEntity search(String s) {
        return null;
    }

    @Override
    public boolean update(BorrowEntity entity) {
        return false;
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
