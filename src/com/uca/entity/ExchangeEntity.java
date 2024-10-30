package com.uca.entity;

import java.sql.Timestamp;

public class ExchangeEntity {
    // On note user1 l'utilisateur qui propose l'échange, donc c'est le user2 qui aura l'offre dans ces offres d'échange (on associe pokemon1 au pokemon que donne user1 et pokemon2 au pokemon que recois user1)
    private UserEntity user1;
    private UserEntity user2;
    private PokemonEntity pokemon1;
    private PokemonEntity pokemon2;

    public ExchangeEntity(){
        // niet !
    }

    public void setUser1(UserEntity user){
        this.user1 = user;
    }

    public void setUser2(UserEntity user){
        this.user2 = user;
    }

    public UserEntity getUser1(){
        return this.user1;
    }

    public UserEntity getUser2(){
        return this.user2;
    }

    public void setPokemon1(PokemonEntity user){
        this.pokemon1 = user;
    }

    public void setPokemon2(PokemonEntity user){
        this.pokemon2 = user;
    }

    public PokemonEntity getPokemon1(){
        return this.pokemon1;
    }

    public PokemonEntity getPokemon2(){
        return this.pokemon2;
    }

}
