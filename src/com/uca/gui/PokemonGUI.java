package com.uca.gui;

import com.uca.core.PokemonCore;
import com.uca.dao.PokemonDAO;
import com.uca.entity.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class PokemonGUI {

    public static String getPokemons(String pseudo) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("pokemons", PokemonCore.getAllPokemons(pseudo));

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("pokemons/pokedex.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String gettoutlespoke() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("pokemons", new PokemonDAO().getAllPokemons());

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("pokemons/pokedex.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String getNewPokemon(String pseudo) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();
        PokemonEntity pokemon = new PokemonEntity();

        try {
            pokemon = PokemonCore.getNewPokemon(pseudo);
            input.put("id", pokemon.getId());
            input.put("name", pokemon.getName());
            input.put("exp", pokemon.getExp());
            input.put("user", pokemon.getUser());
            input.put("available", true);
        } catch (Exception e) {
            System.out.println(e);
            input.put("available", false);
            input.put("erreur", e.getMessage());
        }

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("pokemons/newpokemon.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
