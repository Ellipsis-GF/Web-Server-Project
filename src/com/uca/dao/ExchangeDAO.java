package com.uca.dao;

import com.uca.entity.PokemonEntity;
import com.uca.entity.UserEntity;
import com.uca.entity.ExchangeEntity;
import com.uca.dao.UserDAO;

import java.sql.*;
import java.util.ArrayList;

public class ExchangeDAO extends _Generic<ExchangeEntity> {

    public ArrayList<ExchangeEntity> getAllExchangeOffers(UserEntity user) {
        ArrayList<ExchangeEntity> entities = new ArrayList<>();
        try {
            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM exchangeoffers WHERE idUser2 = ?;");
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ExchangeEntity entity = new ExchangeEntity();
                entity.setUser1(new UserDAO().getUserById(resultSet.getInt("idUser1")));
                entity.setPokemon1(new PokemonDAO().getPokemonById(resultSet.getInt("idPokemon1")));
                entity.setUser2(new UserDAO().getUserById(resultSet.getInt("idUser2")));
                entity.setPokemon2(new PokemonDAO().getPokemonById(resultSet.getInt("idPokemon2")));

                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public ExchangeEntity getExchangeOffersByPokemon2Id(int pokemon2Id) {
        ExchangeEntity entity = new ExchangeEntity();
        try {
            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM exchangeoffers WHERE idPokemon2 = ?;");
            statement.setInt(1, pokemon2Id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entity.setUser1(new UserDAO().getUserById(resultSet.getInt("idUser1")));
                entity.setPokemon1(new PokemonDAO().getPokemonById(resultSet.getInt("idPokemon1")));
                entity.setUser2(new UserDAO().getUserById(resultSet.getInt("idUser2")));
                entity.setPokemon2(new PokemonDAO().getPokemonById(resultSet.getInt("idPokemon2")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public void acceptExchangeOffer(ExchangeEntity exchange) {
        System.out.println("ca va jusqu au DAO !");
        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE pokemons SET idUser = ? WHERE id = ?; UPDATE pokemons SET idUser = ? WHERE id = ?;");
            statement.setInt(1, exchange.getUser2().getId());
            statement.setInt(2, exchange.getPokemon1().getId());
            statement.setInt(3, exchange.getUser1().getId());
            statement.setInt(4, exchange.getPokemon2().getId());
            statement.executeUpdate();
        
            delete(exchange);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ExchangeEntity create(ExchangeEntity exchange) {
        Connection connection = _Connector.getInstance();
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement("INSERT INTO exchangeoffers (idUser1, idPokemon1, idUser2, idPokemon2) VALUES(?, ?, ?, ?);");
            statement.setInt(1, exchange.getUser1().getId());
            statement.setInt(2, exchange.getPokemon1().getId());
            statement.setInt(3, exchange.getUser2().getId());
            statement.setInt(4, exchange.getPokemon2().getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exchange;
    }

    @Override
    public void delete(ExchangeEntity obj) {
        Connection connection = _Connector.getInstance();
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement("DELETE FROM exchangeoffers WHERE idPokemon1 = ?;");
            statement.setInt(1, obj.getPokemon1().getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
