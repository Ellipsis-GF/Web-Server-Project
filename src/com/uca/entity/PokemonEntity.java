package com.uca.entity;

import java.sql.Timestamp;

public class PokemonEntity {
    private int id;
    private UserEntity user;
    private String name;
    private int exp;

    public PokemonEntity(){
        // niet !
    }

    public void setId(int id){
        this.id = id;
    }

    public void setUser(UserEntity user){
        this.user = user;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setExp(int exp){
        this.exp = exp;
    }
    
    public int getId(){
        return this.id;
    }

    public UserEntity getUser(){
        return this.user;
    }

    public String getName(){
        return this.name;
    }

    public int getExp(){
        return this.exp;
    }
}
