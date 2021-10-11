package com.krugercorp.ec.vacunacion.otros;

public interface ValidadorStrategy {
    boolean validarCampo(String valor, String regex);
}
