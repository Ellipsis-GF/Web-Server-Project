package com.uca.core;

import com.uca.dao.UserDAO;
import com.uca.entity.UserEntity;
import spark.Request;
import spark.Session;

import java.sql.Timestamp;
import java.util.ArrayList;

public class UserCore {

    public static ArrayList<UserEntity> getAllUsers() {
        return new UserDAO().getAllUsers();
    }

    public static UserEntity getUserById(int id) {
        return new UserDAO().getUserById(id);
    }

    public static UserEntity Register(Request request) throws Exception{
        ArrayList<UserEntity> listUsers = getAllUsers();
        for (UserEntity u : listUsers){
            if ( (u.getEmail().equals(request.queryParams("email")) ) || (u.getPseudo().equals(request.queryParams("pseudo"))) ){
                throw new Exception("L'email ou le pseudo est déjà enregistré");
            }
        }
        UserEntity user = new UserEntity();
        user.setFirstName(request.queryParams("firstname"));
        user.setLastName(request.queryParams("lastname"));
        user.setEmail(request.queryParams("email"));
        user.setPassword(request.queryParams("password"));
        user.setPseudo(request.queryParams("pseudo"));
        user.setLastDaily(new Timestamp(0));
        return new UserDAO().create(user);
    }

    public static UserEntity Login(Request request) throws Exception{
        ArrayList<UserEntity> listUsers = getAllUsers();
        for (UserEntity u : listUsers){
            if ( (u.getEmail().equals(request.queryParams("email")) ) || (u.getPseudo().equals(request.queryParams("pseudo"))) ){
                if (u.getPassword().equals(request.queryParams("password"))) {
                    Session session = request.session();
                    session.attribute("Session", request.queryParams("pseudo"));
                    return u;
                }
            }
        }
        throw new Exception("Les identifiants ne sont pas valide");
    }

}
