package repository.custom.impl;

import entity.MemberEntity;
import repository.custom.MemberDao;
import repository.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {
    @Override
    public boolean save(MemberEntity entity) {
        String SQL = "INSERT INTO members VALUES  (?, ?, ?, ?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, entity.getId());
            preparedStatement.setObject(2, entity.getName());
            preparedStatement.setObject(3, entity.getContactInfo());
            preparedStatement.setObject(4, entity.getMembershipDate());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MemberEntity search(String s) {
        String SQL = "SELECT *from members WHERE member_id='" + s + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            if (resultSet.next()) {
                return new MemberEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public boolean update(MemberEntity entity) {
        String SQL = "UPDATE members SET name=?, contact_info=?, membership_date=? WHERE member_id=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getContactInfo());
            preparedStatement.setObject(3, entity.getMembershipDate());
            preparedStatement.setObject(4, entity.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String s) {
        String SQL = "DELETE from members WHERE member_id='" + s + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            return statement.executeUpdate(SQL) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MemberEntity> getAll() {
        List<MemberEntity> memberEntities = new ArrayList<>();

        String SQL = "SELECT *from members";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);

            while (resultSet.next()) {
                MemberEntity memberEntity = new MemberEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
                        memberEntities.add(memberEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return memberEntities;
    }

}
