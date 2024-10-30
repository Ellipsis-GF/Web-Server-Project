package com.uca.dao;

import com.uca.entity.UserEntity;

import java.sql.*;
import java.util.*;

public class UserDAO extends _Generic<UserEntity> {

    public ArrayList<UserEntity> getAllUsers() {
        ArrayList<UserEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM users ORDER BY id ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserEntity entity = new UserEntity();
                entity.setId(resultSet.getInt("id"));
                entity.setFirstName(resultSet.getString("firstname"));
                entity.setLastName(resultSet.getString("lastname"));
                entity.setPseudo(resultSet.getString("pseudo"));
                entity.setEmail(resultSet.getString("email"));
                entity.setPassword(resultSet.getString("password"));
                entity.setLastDaily(resultSet.getTimestamp("lastDaily"));

                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    public UserEntity getUserById(int id) {
        UserEntity entity = new UserEntity();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entity.setId(resultSet.getInt("id"));
                entity.setFirstName(resultSet.getString("firstname"));
                entity.setLastName(resultSet.getString("lastname"));
                entity.setPseudo(resultSet.getString("pseudo"));
                entity.setEmail(resultSet.getString("email"));
                entity.setPassword(resultSet.getString("password"));
                entity.setLastDaily(resultSet.getTimestamp("lastDaily"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public UserEntity getUserByPseudo(String pseudo) {
        UserEntity entity = new UserEntity();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM users WHERE pseudo = ?");
            preparedStatement.setString(1, pseudo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entity.setId(resultSet.getInt("id"));
                entity.setFirstName(resultSet.getString("firstname"));
                entity.setLastName(resultSet.getString("lastname"));
                entity.setPseudo(resultSet.getString("pseudo"));
                entity.setEmail(resultSet.getString("email"));
                entity.setPassword(resultSet.getString("password"));
                entity.setLastDaily(resultSet.getTimestamp("lastDaily"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public void updateLastDaily(UserEntity user){
        Connection connection = _Connector.getInstance();
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement("UPDATE users SET lastDaily = NOW() WHERE id = ?");
            statement.setInt(1, user.getId());
            statement.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public UserEntity create(UserEntity user) {
        Connection connection = _Connector.getInstance();
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement("INSERT INTO users(firstname, lastname, email, pseudo, password, lastDaily) VALUES(?, ?, ?, ?, ?, ?);");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPseudo());
            statement.setString(5, user.getPassword());
            statement.setTimestamp(6, user.getLastDaily());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(UserEntity obj) {
        //TODO !
    }
}
