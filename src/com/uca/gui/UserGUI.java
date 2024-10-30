package com.uca.gui;

import com.uca.core.UserCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import spark.Request;

public class UserGUI {

    public static String getAllUsers() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("users", UserCore.getAllUsers());

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/users.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }


    public static String loadRegister() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();
    
        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/register.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(null, output);
    
        return output.toString();
    }

    public static String loadLogin() throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();
    
        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/login.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(null, output);
    
        return output.toString();
    }

    public static String Register(Request request) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        try{
            UserCore.Register(request);
            input.put("valide", "ok");
        } catch (Exception e){
            input.put("valide", e.getMessage());
        }
        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/successRegister.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);
    
        return output.toString();
    }

    public static String Login(Request request) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        try{
            UserCore.Login(request);
            input.put("valide", "ok");
        } catch (Exception e){
            input.put("valide", e.getMessage());
        }
        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/successLogin.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);
    
        return output.toString();
    }
}
