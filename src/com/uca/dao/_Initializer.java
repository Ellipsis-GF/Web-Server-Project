package com.uca.dao;

import java.sql.*;

public class _Initializer {

    public static void Init(){
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;

            //Init user table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id int primary key auto_increment, firstname varchar(100), lastname varchar(100), email varchar(100), pseudo varchar(100), password varchar(100), lastDaily timestamp); ");
            statement.executeUpdate();

            //Init pokemon table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS pokemons (id int primary key auto_increment, idUser int, name varchar(100), exp int(100), foreign key (idUser) references users(id)); ");
            statement.executeUpdate();

            //Init exchangeoffers table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS exchangeoffers (idUser1 int, idUser2 int, idPokemon1 int, idPokemon2 int, primary key(idPokemon2), foreign key (idUser1) references users(id), foreign key (idUser2) references users(id), foreign key (idPokemon1) references pokemons(id), foreign key (idPokemon2) references pokemons(id)); ");
            statement.executeUpdate();

            //Todo Remove me !
        } catch (Exception e){
            System.out.println(e.toString());
            throw new RuntimeException("could not create database !");
        }
    }

    
}
