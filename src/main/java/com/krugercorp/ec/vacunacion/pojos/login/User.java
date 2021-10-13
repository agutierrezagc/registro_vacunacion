package com.krugercorp.ec.vacunacion.pojos.login;

public class User {

    private String token;
    private boolean cambiar;

    public User() {
    }

    public User(String token, boolean cambiar) {
        this.token = token;
        this.cambiar = cambiar;
    }

//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isCambiar() {
        return cambiar;
    }

    public void setCambiar(boolean cambiar) {
        this.cambiar = cambiar;
    }
}
