package com.krugercorp.ec.vacunacion.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * validar formato de una cadena con una expresion regualr
     * @param regex     extructura regex
     * @param cadena    cadena a verificar
     * @return  boolean
     */
    public static boolean validaFormato(String cadena, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }


    /**
     * Genera el pasword hash
     * @param password se requiere la plabra clave
     * @return      retorna el Token generado
     * @throws NoSuchAlgorithmException     exepcion que puede generarse
     * @throws InvalidKeySpecException      exepcion que puede generarse
     */
    public static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    /**
     * Funcion agrega salt Randomica
     * @return retorna numeros en byte[]
     * @throws NoSuchAlgorithmException exepcion que puede generarse
     */
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }


    /**
     * Funcion para convertir byte[] a cadena exagecimal
     * @param array     byte[]
     * @return      cadena exagecimal
     * @throws NoSuchAlgorithmException     exepcion que puede generarse
     */
    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

    /**
     * funcion para generar nombre de usuario
     * @param nombres   nombres
     * @param cedula    cedula
     * @return nombre de usuario con formato [primer_nombre].[cedula_numero]
     */
    public static String generaUsuario(String nombres, String cedula){
        int numero = Integer.parseInt(cedula);
        String usuario = nombres.split(" ")[0];
        usuario +="."+numero;
        return usuario;
    }

    /**
     * Funcion para validar la palabra clave
     * @param originalPassword  palabra clave ingresada
     * @param storedPassword    palabra clave almacenada en BD
     * @return      valor booleano
     * @throws NoSuchAlgorithmException exepcion que puede generarse
     * @throws InvalidKeySpecException  exepcion que puede generarse
     */
    public static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    /**
     * Funcion para convertir de cadena exagecimal a byte[]
     * @param hex   cadena Exagecimal
     * @return      byte[]
     * @throws NoSuchAlgorithmException exepcion que puede generarse
     */
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

}
