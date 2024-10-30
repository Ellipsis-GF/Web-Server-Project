package com.uca.core;

import com.uca.dao.PokemonDAO;
import com.uca.dao.UserDAO;
import com.uca.entity.PokemonEntity;
import com.uca.entity.UserEntity;
import com.uca.core.UserCore;
import spark.Session;

import java.net.*;
import java.io.*;
import org.json.*;
import java.util.*;
import java.sql.Timestamp;

public class PokemonCore {

    public static String convertirTempsEnFormatHHMMSS(long tempsEnMillisecondes) {
        long secondes = tempsEnMillisecondes / 1000;
        long heures = secondes / 3600;
        long minutes = (secondes % 3600) / 60;
        long secondesRestantes = (secondes % 3600) % 60;

        String tempsFormatte = String.format("%02d:%02d:%02d", heures, minutes, secondesRestantes);
        return tempsFormatte;
    }

    public static ArrayList<PokemonEntity> getAllPokemons(String pseudo) {
        UserEntity user = new UserEntity();
        // On recupère l'objet UserEntity lié au pseudo 
        for (UserEntity u : UserCore.getAllUsers()){
            System.out.println("la on cherche l user en question avec l'id " + u.getId() + "et le resultat " + u.getPseudo() + pseudo);
            if (u.getPseudo().equals(pseudo)){
                user.copy(u);
            }
        }
        return new PokemonDAO().getAllPokemons(user);
    }

    public static ArrayList<PokemonEntity> getPokemonsByName(String name){
        return new PokemonDAO().getPokemonsByName(name);
    }

    public static PokemonEntity getNewPokemon(String pseudo) throws Exception {
        UserEntity user = new UserEntity();
        // On recupère l'objet UserEntity lié au pseudo 
        for (UserEntity u : UserCore.getAllUsers()){
            System.out.println("la on cherche l user en question avec l'id " + u.getId() + "et le resultat " + u.getPseudo() + pseudo);
            if (u.getPseudo().equals(pseudo)){
                user.copy(u);
            }
        }

        // On vérifie que l'utilisateur a la possibilité de récupérer un nouveau pokémon quotidien
        // 86400 secondes dans une journée et 1000 milisecondes dans une seconde
        Timestamp ecart = new Timestamp(new Timestamp(System.currentTimeMillis()).getTime() - user.getLastDaily().getTime());
        System.out.println(" L ECART DE TEMPS EN MILISECONDES : " + ecart.getTime() + " AVEC LE TEMPS ACTUEL : " + new Timestamp(System.currentTimeMillis()).getTime() + " ET LE TEMPS AU LASTDAILY " + user.getLastDaily().getTime());
        if (ecart.getTime() < (86400 * 1000)){
            throw new Exception("Il est trop tot pour obtenir votre pokemon quotidien, veuillez revenir dans " + convertirTempsEnFormatHHMMSS( (86400 * 1000) - ecart.getTime()));
        }

        // On prend un pokémon aléatoire entre 1 et 1010
        Random rand = new Random();
        int id = rand.nextInt(1011);

        // On établit la connexion avec l'api
        URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();
        conn.disconnect();
        // On convertit la réponse en JSONObject
        JSONObject jsonResponse = new JSONObject(response.toString());

        // On créer le nouveau pokémon aléatoire en lui assignant les attribut nom, exp
        String name = jsonResponse.getJSONObject("species").getString("name");
        PokemonEntity pokemonAleatoire = new PokemonEntity();
        pokemonAleatoire.setName(name);
        pokemonAleatoire.setExp(1);
        pokemonAleatoire.setUser(user);

        // On stock le pokémon dan la base de donnée
        new PokemonDAO().create(pokemonAleatoire);
        new UserDAO().updateLastDaily(user);

        return pokemonAleatoire;
    }
}
