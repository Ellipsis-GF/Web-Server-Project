package com.uca.entity;

import java.sql.Timestamp;

public class UserEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String pseudo;
    private String password;
    private int id;
    private Timestamp lastDaily;

    public UserEntity() {
        //Ignored !
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Timestamp getLastDaily() {
        return this.lastDaily;
    }

    public void setLastDaily(Timestamp date) {
        this.lastDaily = date;
    }

    public void copy(UserEntity u){
        this.pseudo = u.getPseudo();
        this.id = u.getId();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.lastDaily = u.getLastDaily();
    }
}
