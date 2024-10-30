package com.uca;

import com.uca.dao.PokemonDAO;
import com.uca.dao._Initializer;
import com.uca.gui.*;
import spark.Session;

import static spark.Spark.*;

public class StartServer {

    public static void main(String[] args) {
        //Configure Spark
        staticFiles.location("/static/");
        port(8081);


        _Initializer.Init();

        //Defining our routes
        get("/", (req, res) -> {
            return HomeGUI.getHome();
        });

        get("/users", (req, res) -> {
            return UserGUI.getAllUsers();
        });
        
        get("/pokedex", (req, res) -> {
            if (req.session().attribute("Session") == null){
                res.redirect("/login");
                return null;
            } else {
                System.out.println("la session" + req.session().attribute("Session"));
                return PokemonGUI.getPokemons(req.session().attribute("Session"));
            }
        });

        get("/newpokemon", (req, res) -> {
            if (req.session().attribute("Session") == null){
                res.redirect("/login");
                return null;
            } else {
                return PokemonGUI.getNewPokemon(req.session().attribute("Session"));
            }
        });

        /* get("/training", (req, res) -> {
            if (req.session().attribute("Session") == null){
                res.redirect("/login");
                return null;
            } else {
                return PokemonGUI.loadLevelUp();
            }
        });

        post("/training", (req, res) -> {
            if (req.session().attribute("Session") == null){
                res.redirect("/login");
                return null;
            } else {
                return PokemonGUI.levelUp(req.session().attribute("Session"));
            }
        }); */

        get("/exchange", (req, res) -> {
            if (req.session().attribute("Session") == null){
                res.redirect("/login");
                return null;
            } else if (req.queryParams("search") == null){
                return ExchangeGUI.loadExchange(req.session().attribute("Session"));
            } else {
                return ExchangeGUI.loadExchange(req.session().attribute("Session"), req.queryParams("search"));
            }
        });

        post("/exchange", (req, res) -> {
            if (req.session().attribute("Session") == null){
                res.redirect("/login");
                return null;
            } else {
                return ExchangeGUI.offerExchange(req, req.session().attribute("Session"));
            }
        });
        
        get("/exchange-offers", (req, res) -> {
            if (req.session().attribute("Session") == null){
                res.redirect("/login");
                return null;
            } else {
                return ExchangeGUI.getAllExchangeOffers(req.session().attribute("Session"));
            }
        });

        post("/exchange-offers", (req, res) -> {
            if (req.session().attribute("Session") == null){
                res.redirect("/login");
                return null;
            } else if (req.queryParams("accept") == null || req.queryParams("pokemon2") == null){
                res.redirect("/exchange");
                return null;
            } else if (req.queryParams("accept").equals("true")){
                return ExchangeGUI.acceptExchangeOffer(req.session().attribute("Session"), req.queryParams("pokemon2"));
            } else if (req.queryParams("accept").equals("false")){
                return ExchangeGUI.denyExchangeOffer(req.session().attribute("Session"), req.queryParams("pokemon2"));
            } else {
                res.redirect("/exchange");
                return null;
            }
        });

        get("/register", (req, res) -> {
            return UserGUI.loadRegister();
        });

        post("/register", (req, res) -> {
            return UserGUI.Register(req);
        });

        get("/login", (req, res) -> {
            return UserGUI.loadLogin();
        });

        post("/login", (req, res) -> {
            return UserGUI.Login(req);
        });

        get("/pokemons", (req, res) -> {
            return PokemonGUI.gettoutlespoke();
        });
    }
}