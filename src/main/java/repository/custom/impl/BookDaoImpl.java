package repository.custom.impl;

import entity.BookEntity;
import org.hibernate.annotations.processing.SQL;
import repository.custom.BookDao;
import repository.db.DBConnection;

import java.sql.*;
import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public boolean save(BookEntity entity) {
        String SQL = "INSERT INTO books VALUES (?, ? ,?, ?, ?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getISBN());
            preparedStatement.setObject(3, entity.getTitle());
            preparedStatement.setObject(4, entity.getAuthor());
            preparedStatement.setObject(5, entity.getGenre());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public BookEntity search(String s) {
        String SQL = "SELECT *from books WHERE Book_ID='" + s + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(BookEntity entity, String s) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        String SQL = "DELETE from books WHERE Book_ID='" + s + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate(SQL) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<BookEntity> getAll() {
        return List.of();
    }

}
