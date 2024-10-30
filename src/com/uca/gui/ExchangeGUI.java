package com.uca.gui;

import com.uca.core.PokemonCore;
import com.uca.core.UserCore;
import com.uca.core.ExchangeCore;
import com.uca.dao.PokemonDAO;
import com.uca.entity.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import spark.Request;

public class ExchangeGUI {

    public static String getAllExchangeOffers(String pseudo) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("exchanges", ExchangeCore.getAllExchangeOffers(pseudo));

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("exchanges/exchange-offers.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String loadExchange(String pseudo) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("pokemons1", PokemonCore.getAllPokemons(pseudo));
        input.put("searched", false);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("exchanges/exchange.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String loadExchange(String pseudo, String search) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("pokemons1", PokemonCore.getAllPokemons(pseudo));
        ArrayList<PokemonEntity> pokemon2 = PokemonCore.getPokemonsByName(search);
        
        if (pokemon2 == null || pokemon2.isEmpty()) {
            input.put("searched", false);
        } else {
            input.put("searched", true);
            input.put("pokemons2", pokemon2);
        }

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("exchanges/exchange.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String offerExchange(Request request, String pseudo) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        try{
            ExchangeCore.offerExchange(request, pseudo);
            input.put("valide", "ok");
        } catch (Exception e){
            input.put("valide", e.getMessage());
        }
        Writer output = new StringWriter();
        Template template = configuration.getTemplate("exchanges/successExchange.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);
    
        return output.toString();
    }

    public static String acceptExchangeOffer(String pseudo, String idPokemon2) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        try{
            ExchangeCore.acceptExchangeOffer(pseudo, idPokemon2);
            input.put("valide", "ok");
        } catch (Exception e){
            input.put("valide", e.getMessage());
        }
        Writer output = new StringWriter();
        Template template = configuration.getTemplate("exchanges/successExchangeOffer.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);
    
        return output.toString();
    }

    public static String denyExchangeOffer(String pseudo, String idPokemon2) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        try{
            ExchangeCore.denyExchangeOffer(pseudo, idPokemon2);
            input.put("valide", "ok");
        } catch (Exception e){
            input.put("valide", e.getMessage());
        }
        Writer output = new StringWriter();
        Template template = configuration.getTemplate("exchanges/successDenyExchangeOffer.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);
    
        return output.toString();
    }
}
