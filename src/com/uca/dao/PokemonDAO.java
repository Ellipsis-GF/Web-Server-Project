package com.uca.dao;

import com.uca.entity.PokemonEntity;
import com.uca.entity.UserEntity;
import com.uca.dao.UserDAO;

import java.sql.*;
import java.util.ArrayList;

public class PokemonDAO extends _Generic<PokemonEntity> {

    public ArrayList<PokemonEntity> getAllPokemons(UserEntity u) {
        ArrayList<PokemonEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM pokemons ORDER BY id ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("idUser") == u.getId()){
                    PokemonEntity entity = new PokemonEntity();
                    entity.setId(resultSet.getInt("id"));
                    entity.setName(resultSet.getString("name"));
                    entity.setExp(resultSet.getInt("exp"));
                    entity.setUser(new UserDAO().getUserById(resultSet.getInt("idUser")));

                    entities.add(entity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public PokemonEntity getPokemonById(int id) {
        PokemonEntity entity = new PokemonEntity();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM pokemons WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entity.setId(resultSet.getInt("id"));
                entity.setName(resultSet.getString("name"));
                entity.setExp(resultSet.getInt("exp"));
                entity.setUser(new UserDAO().getUserById(resultSet.getInt("idUser")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public ArrayList<PokemonEntity> getAllPokemons(){
        ArrayList<PokemonEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM pokemons ORDER BY id ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                    PokemonEntity entity = new PokemonEntity();
                    entity.setId(resultSet.getInt("id"));
                    entity.setName(resultSet.getString("name"));
                    entity.setExp(resultSet.getInt("exp"));
                    entity.setUser(new UserDAO().getUserById(resultSet.getInt("idUser")));

                    entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public ArrayList<PokemonEntity> getPokemonsByName(String name) {
        ArrayList<PokemonEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM pokemons WHERE name = ?;");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                    PokemonEntity entity = new PokemonEntity();
                    entity.setId(resultSet.getInt("id"));
                    entity.setName(resultSet.getString("name"));
                    entity.setExp(resultSet.getInt("exp"));
                    entity.setUser(new UserDAO().getUserById(resultSet.getInt("idUser")));

                    entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public PokemonEntity create(PokemonEntity pokemon) {
        Connection connection = _Connector.getInstance();
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement("INSERT INTO pokemons(idUser, name, exp) VALUES(?, ?, ?);");
            statement.setInt(1, pokemon.getUser().getId());
            statement.setString(2, pokemon.getName());
            statement.setInt(3, pokemon.getExp());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokemon;
    }

    @Override
    public void delete(PokemonEntity obj) {
        //TODO !
    }
}
