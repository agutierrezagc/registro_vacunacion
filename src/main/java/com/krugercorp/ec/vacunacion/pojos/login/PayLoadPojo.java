package com.krugercorp.ec.vacunacion.pojos.login;

import java.math.BigInteger;
import java.util.List;

public class PayLoadPojo {
    private String name;
    private String[] rol;
    private String email;
    //    private long iat;
    private long exp;

    public PayLoadPojo() {
    }

    public PayLoadPojo(String name, String[] rol, String email, long exp) {
        this.name = name;
        this.rol = rol;
        this.email = email;
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getRol() {
        return rol;
    }

    public void setRol(String[] rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }
}
