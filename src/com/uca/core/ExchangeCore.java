package com.uca.core;

import com.uca.dao.UserDAO;
import com.uca.dao.PokemonDAO;
import com.uca.dao.ExchangeDAO;
import com.uca.entity.UserEntity;
import com.uca.entity.PokemonEntity;
import com.uca.entity.ExchangeEntity;
import spark.Request;
import spark.Session;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ExchangeCore {

    public static String enleveEspace(String string){
        String resultat = new String();
        if(string.length() > 3){
            resultat += string.substring(0, (string.length() - 4)) + string.substring((string.length() - 3));
            return resultat;
        } else {
            return string;
        }
    }

    public static ArrayList<ExchangeEntity> getAllExchangeOffers(String pseudo){
        UserEntity user = new UserDAO().getUserByPseudo(pseudo);
        return new ExchangeDAO().getAllExchangeOffers(user);
    }

    public static ExchangeEntity offerExchange(Request request, String pseudo) throws Exception{
        int idPokemon1 = Integer.parseInt(request.queryParams("idPokemon1").replaceAll("\\s", ""));
        int idPokemon2 = Integer.parseInt(request.queryParams("idPokemon2").replaceAll("\\s", ""));

        // On verifie que l'utilisateur qui propose l'échange possède bien le pokémon qu'il souhaite échanger
        // On vérifie que le pokémon demandé par l'utilisateur qui propose l'échange n'est pas déjà détenu par ce dernier
        Boolean possede1 = false;
        Boolean possede2 = false;
        for (PokemonEntity p : new PokemonDAO().getAllPokemons(new UserDAO().getUserByPseudo(pseudo))){
            if (p.getId() == idPokemon1){
                possede1 = true;
            } if (p.getId() == idPokemon2){
                possede2 = true;
            }
        }
        if(!possede1){
            throw new Exception("Vous ne possédez pas le pokémon que vous voulez échanger");
        } else if (possede2){
            throw new Exception("Vous possédez déjà le pokémon que vous voulez recevoir");
        }
        // On gère les erreurs liées à une non unicité dans la table
        PokemonEntity pokemon1 = new PokemonDAO().getPokemonById(idPokemon1);
        PokemonEntity pokemon2 = new PokemonDAO().getPokemonById(idPokemon2);

        for (ExchangeEntity exchange : new ExchangeDAO().getAllExchangeOffers(pokemon2.getUser())){
            if (exchange.getUser1().getId() == pokemon1.getUser().getId() && 
            exchange.getUser2().getId() == pokemon2.getUser().getId() && 
            exchange.getPokemon1().getId() == pokemon1.getId() &&
            exchange.getPokemon2().getId() == pokemon2.getId()){
                throw new Exception("L'offre d'échange à déjà été envoyé");
            }
        }

        // On inscrit enfin l'échange dans la base de donnée si toutes les conditions sont validées
        ExchangeEntity entity = new ExchangeEntity();
        entity.setPokemon1(pokemon1);
        entity.setPokemon2(pokemon2);
        entity.setUser1(pokemon1.getUser());
        entity.setUser2(pokemon2.getUser());
        return new ExchangeDAO().create(entity);
    }

    public static void acceptExchangeOffer(String pseudo, String Pokemon2) throws Exception {
        // On vérifie que l'utilisateur correspond bien à celui concerné par l'offre d'échange sinon faille de sécurité (l'utilisateur pourrait accepter lui meme une offre qu il a envoyé)
        System.out.println("cest le parseint le probleme! " + enleveEspace(Pokemon2));
        int idPokemon2 = Integer.parseInt(enleveEspace(Pokemon2));

        Boolean possede = false;
        System.out.println("ca va jusqu au Core avant get all pokemons!");
        for (PokemonEntity p : new PokemonDAO().getAllPokemons(new UserDAO().getUserByPseudo(pseudo))){
            System.out.println("ca va jusqu au Core apres get all pokemons!");
            if (p.getId() == idPokemon2){
                possede = true;
            }
        }
        if(!possede){
            throw new Exception("Vous ne possédez pas le pokémon que vous voulez échanger");
        }
        System.out.println("ca va jusqu au DAO juste avant getExchangeOffersByPokemon2Id !");
        // On fait changer les pokemon de dresseur dans la table pokemons et on suprime l'offre de la table exchangeoffers
        ExchangeEntity exchange = new ExchangeDAO().getExchangeOffersByPokemon2Id(idPokemon2);
        new ExchangeDAO().acceptExchangeOffer(exchange);
    }

    public static void denyExchangeOffer(String pseudo, String Pokemon2) throws Exception {
        // On vérifie que l'utilisateur correspond bien à celui concerné par l'offre d'échange sinon faille de sécurité (l'utilisateur pourrait accepter lui meme une offre qu il a envoyé)
        int idPokemon2 = Integer.parseInt(Pokemon2.replaceAll(" ", ""));

        Boolean possede = false;
        for (PokemonEntity p : new PokemonDAO().getAllPokemons(new UserDAO().getUserByPseudo(pseudo))){
            if (p.getId() == idPokemon2){
                possede = true;
            }
        }
        if(!possede){
            throw new Exception("Vous n'êtes pas concerné par l'échange que vous voulez refuser'");
        }
        // On fait changer les pokemon de dresseur dans la table pokemons et on suprime l'offre de la table exchangeoffers
        ExchangeEntity exchange = new ExchangeDAO().getExchangeOffersByPokemon2Id(idPokemon2);
        new ExchangeDAO().delete(exchange);
    }
}