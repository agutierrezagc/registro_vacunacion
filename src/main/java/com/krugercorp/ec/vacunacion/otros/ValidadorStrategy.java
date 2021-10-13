package com.krugercorp.ec.vacunacion.otros;

/**
 * interface para validacion de campo (validator strategy)
 */
public interface ValidadorStrategy {
    boolean validarCampo(String valor, String regex);
}
