package repository.custom.impl;

import entity.BookEntity;
import repository.custom.BookDao;
import repository.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public boolean save(BookEntity entity) {
        String SQL = "INSERT INTO books VALUES (?, ? ,?, ?, ?, ?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getISBN());
            preparedStatement.setObject(3, entity.getTitle());
            preparedStatement.setObject(4, entity.getAuthor());
            preparedStatement.setObject(5, entity.getGenre());
            preparedStatement.setObject(6, entity.getAvailability());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookEntity search(String s) {
        String SQL = "SELECT *from books WHERE book_id='" + s + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            if (resultSet.next()) {
                return new BookEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(BookEntity entity) {
        String SQL = "UPDATE books SET isbn =?, title =?, author =?, genre =?, availability =? WHERE book_id = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, entity.getISBN());
            preparedStatement.setObject(2, entity.getTitle());
            preparedStatement.setObject(3, entity.getAuthor());
            preparedStatement.setObject(4, entity.getGenre());
            preparedStatement.setObject(5, entity.getAvailability());
            preparedStatement.setObject(6, entity.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String s) {
        String SQL = "DELETE from books WHERE book_id='" + s + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate(SQL) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookEntity> getAll() {
        ArrayList<BookEntity> bookEntities = new ArrayList<>();

        String SQL = "SELECT *from books";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                BookEntity bookEntity = new BookEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                bookEntities.add(bookEntity);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookEntities;
    }

}
